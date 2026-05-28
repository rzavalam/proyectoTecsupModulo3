package com.cine.reservas.application.services;

import com.cine.reservas.domain.model.Reserva;
import com.cine.reservas.domain.ports.inbound.CancelarReservaUseCase;
import com.cine.reservas.domain.ports.outbound.FuncionQueryPort;
import com.cine.reservas.domain.ports.outbound.ReservaRepositoryPort;
import com.cine.reservas.domain.exceptions.ReservaNoEncontradaException;
import com.cine.shared.domain.DomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Caso de uso: Cancelar Reserva.
 * Al cancelar: libera asientos en Funcion e inicia devolución (eventos de dominio).
 */
@Service
@Transactional
public class CancelarReservaService implements CancelarReservaUseCase {

    private final ReservaRepositoryPort reservaRepository;
    private final FuncionQueryPort funcionQuery;
    private final ApplicationEventPublisher eventPublisher;

    public CancelarReservaService(ReservaRepositoryPort reservaRepository,
                                   FuncionQueryPort funcionQuery,
                                   ApplicationEventPublisher eventPublisher) {
        this.reservaRepository = reservaRepository;
        this.funcionQuery = funcionQuery;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public CancelacionResponse cancelar(CancelarReservaCommand command) {
        Reserva reserva = reservaRepository.buscarPorId(command.reservaId())
                .orElseThrow(() -> new ReservaNoEncontradaException(command.reservaId()));

        LocalDateTime inicioFuncion = funcionQuery.obtenerInicioFuncion(reserva.getFuncionId());

        // Cancelar el agregado — valida invariante 4 (hasta 1 hora antes)
        reserva.cancelar(inicioFuncion);

        // Liberar asientos en el agregado Funcion (invariante 7)
        reserva.getAsientos().forEach(asiento ->
                funcionQuery.liberarAsiento(reserva.getFuncionId(), asiento.getNumero()));

        reservaRepository.guardar(reserva);

        List<DomainEvent> events = reserva.pullDomainEvents();
        events.forEach(eventPublisher::publishEvent);

        return new CancelacionResponse(
                reserva.getId(),
                reserva.getEstado().name(),
                "Reserva cancelada. Los asientos han sido liberados y se iniciará la devolución."
        );
    }
}
