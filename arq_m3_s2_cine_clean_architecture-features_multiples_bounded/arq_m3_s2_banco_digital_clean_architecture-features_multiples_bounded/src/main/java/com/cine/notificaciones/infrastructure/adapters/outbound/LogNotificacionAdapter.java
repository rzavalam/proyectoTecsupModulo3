package com.cine.notificaciones.infrastructure.adapters.outbound;

import com.cine.notificaciones.domain.model.Confirmacion;
import com.cine.notificaciones.domain.ports.outbound.NotificacionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Implementación stub del puerto de notificación.
 * En producción se reemplazaría por un adaptador de email/SMS real.
 */
@Component
public class LogNotificacionAdapter implements NotificacionPort {

    private static final Logger log = LoggerFactory.getLogger(LogNotificacionAdapter.class);

    @Override
    public void enviar(Confirmacion confirmacion) {
        log.info("[NOTIFICACION][{}] Cliente={} | Reserva={} | Mensaje={}",
                confirmacion.getTipo(),
                confirmacion.getClienteId(),
                confirmacion.getReservaId(),
                confirmacion.getMensaje());
    }
}
