package com.cine.clientes.infrastructure.adapters.inbound;

import com.cine.clientes.domain.ports.inbound.RegistrarClienteUseCase;
import com.cine.clientes.domain.ports.inbound.RegistrarClienteUseCase.ClienteResponse;
import com.cine.clientes.domain.ports.inbound.RegistrarClienteUseCase.RegistrarClienteCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final RegistrarClienteUseCase registrarClienteUseCase;

    public ClienteController(RegistrarClienteUseCase registrarClienteUseCase) {
        this.registrarClienteUseCase = registrarClienteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> registrar(@RequestBody RegistrarClienteRequest request) {
        ClienteResponse response = registrarClienteUseCase.registrar(
                new RegistrarClienteCommand(request.clienteId(), request.nombre(), request.correo()));
        return ResponseEntity.ok(response);
    }

    record RegistrarClienteRequest(String clienteId, String nombre, String correo) {}
}
