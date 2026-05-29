package com.cine.clientes.infrastructure.adapters.outbound;

import com.cine.clientes.domain.model.Cliente;
import com.cine.clientes.domain.ports.outbound.ClienteRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteJpaAdapter implements ClienteRepositoryPort {

    private final ClienteSpringRepository repository;

    public ClienteJpaAdapter(ClienteSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public void guardar(Cliente cliente) {
        repository.save(ClienteJpaEntity.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .correo(cliente.getCorreo())
                .build());
    }

    @Override
    public Optional<Cliente> buscarPorId(String clienteId) {
        return repository.findById(clienteId)
                .map(e -> Cliente.reconstituir(e.getId(), e.getNombre(), e.getCorreo()));
    }
}
