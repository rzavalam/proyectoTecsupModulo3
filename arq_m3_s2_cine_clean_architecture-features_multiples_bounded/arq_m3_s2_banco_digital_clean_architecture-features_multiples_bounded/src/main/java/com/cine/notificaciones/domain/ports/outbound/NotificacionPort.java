package com.cine.notificaciones.domain.ports.outbound;

import com.cine.notificaciones.domain.model.Confirmacion;

public interface NotificacionPort {

    void enviar(Confirmacion confirmacion);
}
