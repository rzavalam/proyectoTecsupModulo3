package com.cine.validacion.domain.ports.outbound;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;

import java.util.List;

public interface DisponibilidadQueryPort {

    boolean todosDisponibles(String funcionId, List<NumeroAsiento> asientos);

    long contarDisponibles(String funcionId);
}
