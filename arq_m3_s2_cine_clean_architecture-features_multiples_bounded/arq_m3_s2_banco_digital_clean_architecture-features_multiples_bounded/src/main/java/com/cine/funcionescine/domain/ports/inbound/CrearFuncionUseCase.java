package com.cine.funcionescine.domain.ports.inbound;

import com.cine.funcionescine.domain.model.vo.HorarioFuncion;
import com.cine.funcionescine.domain.model.vo.Precio;
import com.cine.funcionescine.domain.model.vo.TipoFuncion;

public interface CrearFuncionUseCase {

    FuncionCreadaResponse crear(CrearFuncionCommand command);

    record CrearFuncionCommand(
            String funcionId,
            String peliculaId,
            String salaId,
            HorarioFuncion horario,
            Precio precio,
            TipoFuncion tipoFuncion
    ) {}

    record FuncionCreadaResponse(
            String funcionId,
            String salaId,
            String peliculaId,
            String horario,
            String tipoFuncion,
            String precio,
            int totalAsientos
    ) {}
}
