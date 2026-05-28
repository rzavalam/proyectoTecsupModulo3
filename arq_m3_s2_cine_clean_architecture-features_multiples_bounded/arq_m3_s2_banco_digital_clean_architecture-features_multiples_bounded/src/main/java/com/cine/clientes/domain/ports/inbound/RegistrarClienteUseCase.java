package com.cine.clientes.domain.ports.inbound;

public interface RegistrarClienteUseCase {

    ClienteResponse registrar(RegistrarClienteCommand command);

    record RegistrarClienteCommand(String clienteId, String nombre, String correo) {}

    record ClienteResponse(String clienteId, String nombre, String correo) {}
}
