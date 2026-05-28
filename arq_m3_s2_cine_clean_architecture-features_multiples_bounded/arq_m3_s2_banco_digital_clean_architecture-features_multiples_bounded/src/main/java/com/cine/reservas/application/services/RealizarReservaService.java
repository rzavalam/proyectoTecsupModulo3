package com.cine.reservas.application.services;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;
import com.cine.reservas.domain.model.AsientoReservado;
import com.cine.reservas.domain.model.Reserva;
import com.cine.reservas.domain.ports.inbound.RealizarReservaUseCase;
import com.cine.reservas.domain.ports.outbound.FuncionQueryPort;
import com.cine.reservas.domain.ports.outbound.ReservaRepositoryPort;
import com.cine.shared.domain.DomainEvent;
import com.cine.validacion.domain.ports.inbound.ValidarReservaUseCase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Caso de uso: Realizar Reserva.
 * Orquesta: validación → reserva de asientos → creación del agregado → aprobación → publicación de eventos.
 */
@Service
@Transactional
public class RealizarReservaService implements RealizarReservaUseCase {

    private final ReservaRepositoryPort reservaRepository;
    private final FuncionQueryPort funcionQuery;
    private final ValidarReservaUseCase validarReserva;
    private final ApplicationEventPublisher eventPublisher;

    public RealizarReservaService(ReservaRepositoryPort reservaRepository,
                                   FuncionQueryPort funcionQuery,
                                   ValidarReservaUseCase validarReserva,
                                   ApplicationEventPublisher eventPublisher) {
        this.reservaRepository = reservaRepository;
        this.funcionQuery = funcionQuery;
        this.validarReserva = validarReserva;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public ReservaResponse realizar(RealizarReservaCommand command) {
        List<NumeroAsiento> numeros = parsearAsientos(command.codigosAsiento());

        LocalDateTime inicioFuncion = funcionQuery.obtenerInicioFuncion(command.funcionId());

        // Partnership: validar reglas de negocio con el BC de Validación
        validarReserva.validar(new ValidarReservaUseCase.ValidarReservaCommand(
                command.funcionId(), inicioFuncion, numeros));

        // Reservar asientos en el agregado Funcion (Customer/Supplier)
        numeros.forEach(n -> funcionQuery.reservarAsiento(command.funcionId(), n));

        List<AsientoReservado> asientos = numeros.stream()
                .map(AsientoReservado::new)
                .toList();

        // Crear el agregado Reserva — valida invariantes 2 y 3
        Reserva reserva = Reserva.iniciar(
                UUID.randomUUID().toString(),
                command.clienteId(),
                command.funcionId(),
                inicioFuncion,
                asientos
        );

        reservaRepository.guardar(reserva);

        // Aprobar de forma sincrónica (emite ReservaAprobada, AsientosConfirmados, PagoRealizado)
        reserva.aprobar();
        reservaRepository.guardar(reserva);

        List<DomainEvent> events = reserva.pullDomainEvents();
        events.forEach(eventPublisher::publishEvent);

        return new ReservaResponse(
                reserva.getId(),
                reserva.getClienteId(),
                reserva.getFuncionId(),
                reserva.getEstado().name(),
                reserva.getCantidadAsientos()
        );
    }

    private List<NumeroAsiento> parsearAsientos(List<String> codigos) {
        return codigos.stream().map(codigo -> {
            if (codigo == null || codigo.length() < 2)
                throw new IllegalArgumentException("Código de asiento inválido: " + codigo);
            char fila = Character.toUpperCase(codigo.charAt(0));
            int numero = Integer.parseInt(codigo.substring(1));
            return new NumeroAsiento(fila, numero);
        }).toList();
    }
}
