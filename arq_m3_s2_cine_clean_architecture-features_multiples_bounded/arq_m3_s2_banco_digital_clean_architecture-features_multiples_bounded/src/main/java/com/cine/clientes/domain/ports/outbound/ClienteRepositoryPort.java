package com.cine.clientes.domain.ports.outbound;

import com.cine.clientes.domain.model.Cliente;

import java.util.Optional;

public interface ClienteRepositoryPort {

    void guardar(Cliente cliente);

    Optional<Cliente> buscarPorId(String clienteId);
}
