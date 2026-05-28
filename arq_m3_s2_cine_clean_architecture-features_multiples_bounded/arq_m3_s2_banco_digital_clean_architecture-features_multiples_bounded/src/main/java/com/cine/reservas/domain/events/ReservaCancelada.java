package com.cine.reservas.domain.events;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;
import com.cine.shared.domain.DomainEvent;

import java.util.List;

public class ReservaCancelada extends DomainEvent {

    private final String reservaId;
    private final String clienteId;
    private final String funcionId;
    private final List<NumeroAsiento> asientosLiberados;

    public ReservaCancelada(String reservaId, String clienteId, String funcionId,
                             List<NumeroAsiento> asientosLiberados) {
        super();
        this.reservaId = reservaId;
        this.clienteId = clienteId;
        this.funcionId = funcionId;
        this.asientosLiberados = List.copyOf(asientosLiberados);
    }

    public String getReservaId() { return reservaId; }
    public String getClienteId() { return clienteId; }
    public String getFuncionId() { return funcionId; }
    public List<NumeroAsiento> getAsientosLiberados() { return asientosLiberados; }

    @Override
    public String getEventType() { return "ReservaCancelada"; }
}
