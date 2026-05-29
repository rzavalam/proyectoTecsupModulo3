package com.cine.notificaciones.application.services;

import com.cine.notificaciones.domain.events.ReservaCanceladaNotificada;
import com.cine.notificaciones.domain.events.ReservaConfirmadaNotificada;
import com.cine.notificaciones.domain.model.Confirmacion;
import com.cine.notificaciones.domain.model.Confirmacion.TipoNotificacion;
import com.cine.notificaciones.domain.ports.inbound.NotificarReservaUseCase;
import com.cine.notificaciones.domain.ports.outbound.NotificacionPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService implements NotificarReservaUseCase {

    private final NotificacionPort notificacionPort;
    private final ApplicationEventPublisher eventPublisher;

    public NotificacionService(NotificacionPort notificacionPort,
                                ApplicationEventPublisher eventPublisher) {
        this.notificacionPort = notificacionPort;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void notificarConfirmacion(String reservaId, String clienteId) {
        Confirmacion confirmacion = new Confirmacion(
                reservaId, clienteId, TipoNotificacion.CONFIRMACION,
                "Su reserva " + reservaId + " ha sido confirmada exitosamente.");
        notificacionPort.enviar(confirmacion);
        eventPublisher.publishEvent(new ReservaConfirmadaNotificada(reservaId, clienteId));
    }

    @Override
    public void notificarCancelacion(String reservaId, String clienteId) {
        Confirmacion confirmacion = new Confirmacion(
                reservaId, clienteId, TipoNotificacion.CANCELACION,
                "Su reserva " + reservaId + " ha sido cancelada. Se procesará la devolución.");
        notificacionPort.enviar(confirmacion);
        eventPublisher.publishEvent(new ReservaCanceladaNotificada(reservaId, clienteId));
    }
}
