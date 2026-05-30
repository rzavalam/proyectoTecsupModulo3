package com.cine.validacion.domain.events;

import com.cine.shared.domain.DomainEvent;

public class ReservaValidada extends DomainEvent {

    private final String funcionId;
    private final int asientosSolicitados;

    public ReservaValidada(String funcionId, int asientosSolicitados) {
        super();
        this.funcionId = funcionId;
        this.asientosSolicitados = asientosSolicitados;
    }

    public String getFuncionId() { return funcionId; }
    public int getAsientosSolicitados() { return asientosSolicitados; }

    @Override
    public String getEventType() { return "ReservaValidada"; }
}
