package com.cine.reservas.domain.events;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;
import com.cine.shared.domain.DomainEvent;

import java.util.List;

public class AsientosConfirmados extends DomainEvent {

    private final String reservaId;
    private final String funcionId;
    private final List<NumeroAsiento> asientos;

    public AsientosConfirmados(String reservaId, String funcionId, List<NumeroAsiento> asientos) {
        super();
        this.reservaId = reservaId;
        this.funcionId = funcionId;
        this.asientos = List.copyOf(asientos);
    }

    public String getReservaId() { return reservaId; }
    public String getFuncionId() { return funcionId; }
    public List<NumeroAsiento> getAsientos() { return asientos; }

    @Override
    public String getEventType() { return "AsientosConfirmados"; }
}
