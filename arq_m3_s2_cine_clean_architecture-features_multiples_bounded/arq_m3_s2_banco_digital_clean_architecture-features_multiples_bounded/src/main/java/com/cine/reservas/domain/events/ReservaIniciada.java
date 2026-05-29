package com.cine.reservas.domain.events;

import com.cine.shared.domain.DomainEvent;

public class ReservaIniciada extends DomainEvent {

    private final String reservaId;
    private final String clienteId;
    private final String funcionId;
    private final int cantidadAsientos;

    public ReservaIniciada(String reservaId, String clienteId, String funcionId, int cantidadAsientos) {
        super();
        this.reservaId = reservaId;
        this.clienteId = clienteId;
        this.funcionId = funcionId;
        this.cantidadAsientos = cantidadAsientos;
    }

    public String getReservaId() { return reservaId; }
    public String getClienteId() { return clienteId; }
    public String getFuncionId() { return funcionId; }
    public int getCantidadAsientos() { return cantidadAsientos; }

    @Override
    public String getEventType() { return "ReservaIniciada"; }
}
