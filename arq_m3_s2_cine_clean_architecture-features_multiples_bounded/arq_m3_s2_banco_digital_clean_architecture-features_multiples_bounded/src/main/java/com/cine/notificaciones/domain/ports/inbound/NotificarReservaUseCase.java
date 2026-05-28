package com.cine.notificaciones.domain.ports.inbound;

public interface NotificarReservaUseCase {

    void notificarConfirmacion(String reservaId, String clienteId);

    void notificarCancelacion(String reservaId, String clienteId);
}
