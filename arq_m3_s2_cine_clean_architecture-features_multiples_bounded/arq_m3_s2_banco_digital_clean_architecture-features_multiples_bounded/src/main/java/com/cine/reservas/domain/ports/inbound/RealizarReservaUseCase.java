package com.cine.reservas.domain.ports.inbound;

import java.util.List;

public interface RealizarReservaUseCase {

    ReservaResponse realizar(RealizarReservaCommand command);

    record RealizarReservaCommand(
            String clienteId,
            String funcionId,
            List<String> codigosAsiento
    ) {}

    record ReservaResponse(
            String reservaId,
            String clienteId,
            String funcionId,
            String estado,
            int cantidadAsientos
    ) {}
}
