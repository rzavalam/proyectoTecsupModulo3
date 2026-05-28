package com.cine.reservas.domain.events;

import com.cine.shared.domain.DomainEvent;

public class PagoRealizado extends DomainEvent {

    private final String reservaId;
    private final String clienteId;
    private final int cantidadAsientos;

    public PagoRealizado(String reservaId, String clienteId, int cantidadAsientos) {
        super();
        this.reservaId = reservaId;
        this.clienteId = clienteId;
        this.cantidadAsientos = cantidadAsientos;
    }

    public String getReservaId() { return reservaId; }
    public String getClienteId() { return clienteId; }
    public int getCantidadAsientos() { return cantidadAsientos; }

    @Override
    public String getEventType() { return "PagoRealizado"; }
}
