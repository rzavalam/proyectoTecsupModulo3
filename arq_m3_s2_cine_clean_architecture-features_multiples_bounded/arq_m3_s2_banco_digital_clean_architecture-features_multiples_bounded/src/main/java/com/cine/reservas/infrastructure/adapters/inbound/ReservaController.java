package com.cine.reservas.infrastructure.adapters.inbound;

import com.cine.reservas.domain.ports.inbound.CancelarReservaUseCase;
import com.cine.reservas.domain.ports.inbound.CancelarReservaUseCase.CancelacionResponse;
import com.cine.reservas.domain.ports.inbound.CancelarReservaUseCase.CancelarReservaCommand;
import com.cine.reservas.domain.ports.inbound.RealizarReservaUseCase;
import com.cine.reservas.domain.ports.inbound.RealizarReservaUseCase.RealizarReservaCommand;
import com.cine.reservas.domain.ports.inbound.RealizarReservaUseCase.ReservaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final RealizarReservaUseCase realizarReservaUseCase;
    private final CancelarReservaUseCase cancelarReservaUseCase;

    public ReservaController(RealizarReservaUseCase realizarReservaUseCase,
                              CancelarReservaUseCase cancelarReservaUseCase) {
        this.realizarReservaUseCase = realizarReservaUseCase;
        this.cancelarReservaUseCase = cancelarReservaUseCase;
    }

    @PostMapping
    public ResponseEntity<ReservaResponse> realizar(@RequestBody RealizarReservaRequest request) {
        ReservaResponse response = realizarReservaUseCase.realizar(
                new RealizarReservaCommand(request.clienteId(), request.funcionId(), request.asientos()));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{reservaId}")
    public ResponseEntity<CancelacionResponse> cancelar(@PathVariable String reservaId) {
        CancelacionResponse response = cancelarReservaUseCase.cancelar(new CancelarReservaCommand(reservaId));
        return ResponseEntity.ok(response);
    }

    record RealizarReservaRequest(String clienteId, String funcionId, List<String> asientos) {}
}
