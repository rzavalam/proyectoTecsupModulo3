package com.cine.funcionescine.domain.events;

import com.cine.funcionescine.domain.model.vo.HorarioFuncion;
import com.cine.funcionescine.domain.model.vo.Precio;
import com.cine.funcionescine.domain.model.vo.TipoFuncion;
import com.cine.shared.domain.DomainEvent;

public class FuncionCreada extends DomainEvent {

    private final String funcionId;
    private final String peliculaId;
    private final String salaId;
    private final HorarioFuncion horario;
    private final TipoFuncion tipoFuncion;
    private final Precio precio;

    public FuncionCreada(String funcionId, String peliculaId, String salaId,
                          HorarioFuncion horario, TipoFuncion tipoFuncion, Precio precio) {
        super();
        this.funcionId = funcionId;
        this.peliculaId = peliculaId;
        this.salaId = salaId;
        this.horario = horario;
        this.tipoFuncion = tipoFuncion;
        this.precio = precio;
    }

    public String getFuncionId() { return funcionId; }
    public String getPeliculaId() { return peliculaId; }
    public String getSalaId() { return salaId; }
    public HorarioFuncion getHorario() { return horario; }
    public TipoFuncion getTipoFuncion() { return tipoFuncion; }
    public Precio getPrecio() { return precio; }

    @Override
    public String getEventType() { return "FuncionCreada"; }
}
