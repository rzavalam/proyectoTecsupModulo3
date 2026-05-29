package com.cine.funcionescine.application.services;

import com.cine.funcionescine.domain.exceptions.SalaNoEncontradaException;
import com.cine.funcionescine.domain.model.Funcion;
import com.cine.funcionescine.domain.model.Sala;
import com.cine.funcionescine.domain.ports.inbound.CrearFuncionUseCase;
import com.cine.funcionescine.domain.ports.outbound.FuncionRepositoryPort;
import com.cine.funcionescine.domain.ports.outbound.SalaRepositoryPort;
import com.cine.shared.domain.DomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CrearFuncionService implements CrearFuncionUseCase {

    private final FuncionRepositoryPort funcionRepository;
    private final SalaRepositoryPort salaRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CrearFuncionService(FuncionRepositoryPort funcionRepository,
                                SalaRepositoryPort salaRepository,
                                ApplicationEventPublisher eventPublisher) {
        this.funcionRepository = funcionRepository;
        this.salaRepository = salaRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public FuncionCreadaResponse crear(CrearFuncionCommand command) {
        Sala sala = salaRepository.buscarPorId(command.salaId())
                .orElseThrow(() -> new SalaNoEncontradaException(command.salaId()));

        Funcion funcion = Funcion.crear(
                command.funcionId(),
                command.peliculaId(),
                sala,
                command.horario(),
                command.precio(),
                command.tipoFuncion()
        );

        funcionRepository.guardar(funcion);

        List<DomainEvent> events = funcion.pullDomainEvents();
        events.forEach(eventPublisher::publishEvent);

        return new FuncionCreadaResponse(
                funcion.getId(),
                funcion.getSala().getId(),
                funcion.getPeliculaId(),
                funcion.getHorario().toString(),
                funcion.getTipoFuncion().name(),
                funcion.getPrecio().toString(),
                funcion.getAsientos().size()
        );
    }
}
