package com.cine.notificaciones.domain.events;

import com.cine.shared.domain.DomainEvent;

public class ReservaCanceladaNotificada extends DomainEvent {

    private final String reservaId;
    private final String clienteId;

    public ReservaCanceladaNotificada(String reservaId, String clienteId) {
        super();
        this.reservaId = reservaId;
        this.clienteId = clienteId;
    }

    public String getReservaId() { return reservaId; }
    public String getClienteId() { return clienteId; }

    @Override
    public String getEventType() { return "ReservaCanceladaNotificada"; }
}
