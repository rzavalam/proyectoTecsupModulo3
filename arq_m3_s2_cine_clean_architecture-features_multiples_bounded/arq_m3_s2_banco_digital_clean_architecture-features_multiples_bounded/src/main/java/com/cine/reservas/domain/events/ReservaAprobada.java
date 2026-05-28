package com.cine.reservas.domain.events;

import com.cine.shared.domain.DomainEvent;

public class ReservaAprobada extends DomainEvent {

    private final String reservaId;
    private final String clienteId;
    private final String funcionId;

    public ReservaAprobada(String reservaId, String clienteId, String funcionId) {
        super();
        this.reservaId = reservaId;
        this.clienteId = clienteId;
        this.funcionId = funcionId;
    }

    public String getReservaId() { return reservaId; }
    public String getClienteId() { return clienteId; }
    public String getFuncionId() { return funcionId; }

    @Override
    public String getEventType() { return "ReservaAprobada"; }
}
