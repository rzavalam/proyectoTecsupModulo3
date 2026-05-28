package com.cine.clientes.domain.exceptions;

public class ClienteNoEncontradoException extends RuntimeException {
    public ClienteNoEncontradoException(String clienteId) {
        super("Cliente no encontrado con ID: " + clienteId);
    }
}
