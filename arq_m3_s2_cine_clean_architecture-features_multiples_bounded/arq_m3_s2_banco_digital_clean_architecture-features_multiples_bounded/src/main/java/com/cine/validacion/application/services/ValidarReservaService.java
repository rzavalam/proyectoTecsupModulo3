package com.cine.validacion.application.services;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;
import com.cine.validacion.domain.events.ReservaValidada;
import com.cine.validacion.domain.exceptions.ValidacionFallidaException;
import com.cine.validacion.domain.ports.inbound.ValidarReservaUseCase;
import com.cine.validacion.domain.ports.outbound.DisponibilidadQueryPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio de aplicación del BC ValidaciónDeReglas.
 * Valida disponibilidad y horario sin contener lógica de negocio propia del dominio.
 */
@Service
public class ValidarReservaService implements ValidarReservaUseCase {

    private final DisponibilidadQueryPort disponibilidadQuery;
    private final ApplicationEventPublisher eventPublisher;

    public ValidarReservaService(DisponibilidadQueryPort disponibilidadQuery,
                                  ApplicationEventPublisher eventPublisher) {
        this.disponibilidadQuery = disponibilidadQuery;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void validar(ValidarReservaCommand command) {
        LocalDateTime ahora = LocalDateTime.now();

        // La anticipación mínima es responsabilidad del agregado Reserva (Inv 2),
        // aquí validamos que la función tenga disponibilidad suficiente.
        if (command.inicioFuncion().isBefore(ahora)) {
            throw new ValidacionFallidaException("La función ya ha iniciado o está en el pasado");
        }

        List<NumeroAsiento> asientos = command.asientosSolicitados();
        if (!disponibilidadQuery.todosDisponibles(command.funcionId(), asientos)) {
            throw new ValidacionFallidaException(
                    "Uno o más asientos solicitados no están disponibles en la función " + command.funcionId());
        }

        eventPublisher.publishEvent(new ReservaValidada(command.funcionId(), asientos.size()));
    }
}
