package com.cine.clientes.application.services;

import com.cine.clientes.domain.model.Cliente;
import com.cine.clientes.domain.ports.inbound.RegistrarClienteUseCase;
import com.cine.clientes.domain.ports.outbound.ClienteRepositoryPort;
import com.cine.shared.domain.DomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegistrarClienteService implements RegistrarClienteUseCase {

    private final ClienteRepositoryPort clienteRepository;
    private final ApplicationEventPublisher eventPublisher;

    public RegistrarClienteService(ClienteRepositoryPort clienteRepository,
                                    ApplicationEventPublisher eventPublisher) {
        this.clienteRepository = clienteRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public ClienteResponse registrar(RegistrarClienteCommand command) {
        Cliente cliente = Cliente.registrar(command.clienteId(), command.nombre(), command.correo());
        clienteRepository.guardar(cliente);

        List<DomainEvent> events = cliente.pullDomainEvents();
        events.forEach(eventPublisher::publishEvent);

        return new ClienteResponse(cliente.getId(), cliente.getNombre(), cliente.getCorreo());
    }
}
