package com.cine.funcionescine.infrastructure.adapters.outbound;

import com.cine.funcionescine.domain.model.Sala;
import com.cine.funcionescine.domain.ports.outbound.SalaRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SalaJpaAdapter implements SalaRepositoryPort {

    private final SalaSpringRepository repository;

    public SalaJpaAdapter(SalaSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public void guardar(Sala sala) {
        repository.save(toEntity(sala));
    }

    @Override
    public Optional<Sala> buscarPorId(String salaId) {
        return repository.findById(salaId).map(this::toDomain);
    }

    private SalaJpaEntity toEntity(Sala sala) {
        return SalaJpaEntity.builder()
                .id(sala.getId())
                .nombre(sala.getNombre())
                .capacidad(sala.getCapacidad())
                .build();
    }

    private Sala toDomain(SalaJpaEntity entity) {
        return new Sala(entity.getId(), entity.getNombre(), entity.getCapacidad());
    }
}
