package com.cine.reservas.domain.ports.inbound;

public interface CancelarReservaUseCase {

    CancelacionResponse cancelar(CancelarReservaCommand command);

    record CancelarReservaCommand(String reservaId) {}

    record CancelacionResponse(
            String reservaId,
            String estado,
            String mensaje
    ) {}
}
