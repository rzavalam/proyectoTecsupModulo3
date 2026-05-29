package com.cine.notificaciones.infrastructure.adapters.inbound;

import com.cine.notificaciones.domain.ports.inbound.NotificarReservaUseCase;
import com.cine.reservas.domain.events.ReservaAprobada;
import com.cine.reservas.domain.events.ReservaCancelada;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Adaptador de entrada del BC Notificaciones.
 * Escucha eventos de dominio publicados por GestiónDeReservas (Published Language).
 * Relación: GestiónDeReservas → Notificaciones (Published Language).
 */
@Component
public class ReservaEventListener {

    private static final Logger log = LoggerFactory.getLogger(ReservaEventListener.class);
    private final NotificarReservaUseCase notificarReserva;

    public ReservaEventListener(NotificarReservaUseCase notificarReserva) {
        this.notificarReserva = notificarReserva;
    }

    @EventListener
    public void onReservaAprobada(ReservaAprobada event) {
        log.info("Evento recibido: {} para reserva {}", event.getEventType(), event.getReservaId());
        notificarReserva.notificarConfirmacion(event.getReservaId(), event.getClienteId());
    }

    @EventListener
    public void onReservaCancelada(ReservaCancelada event) {
        log.info("Evento recibido: {} para reserva {}", event.getEventType(), event.getReservaId());
        notificarReserva.notificarCancelacion(event.getReservaId(), event.getClienteId());
    }
}
