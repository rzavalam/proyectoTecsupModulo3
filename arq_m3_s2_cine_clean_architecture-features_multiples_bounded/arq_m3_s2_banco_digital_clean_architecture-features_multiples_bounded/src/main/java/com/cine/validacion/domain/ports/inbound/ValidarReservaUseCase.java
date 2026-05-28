package com.cine.validacion.domain.ports.inbound;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Puerto de entrada del BC ValidaciónDeReglas.
 * Partnership con GestiónDeReservas.
 */
public interface ValidarReservaUseCase {

    void validar(ValidarReservaCommand command);

    record ValidarReservaCommand(
            String funcionId,
            LocalDateTime inicioFuncion,
            List<NumeroAsiento> asientosSolicitados
    ) {}
}
