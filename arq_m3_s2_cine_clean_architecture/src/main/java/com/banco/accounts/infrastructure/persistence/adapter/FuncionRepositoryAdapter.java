package com.banco.accounts.infrastructure.persistence.adapter;

import com.banco.accounts.domain.model.FuncionCine;
import com.banco.accounts.domain.repository.FuncionRepository;
import com.banco.accounts.infrastructure.persistence.entity.FuncionCineEntity;
import com.banco.accounts.infrastructure.persistence.mapper.FuncionMapper;
import com.banco.accounts.infrastructure.persistence.repository.JpaFuncionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FuncionRepositoryAdapter implements FuncionRepository {

    private final JpaFuncionRepository jpaFuncionRepository;
    private final FuncionMapper funcionMapper;

    @Override
    public FuncionCine guardar(FuncionCine funcionCine) {

        FuncionCineEntity entity =
                funcionMapper.toEntity(funcionCine);

        FuncionCineEntity saved =
                jpaFuncionRepository.save(entity);

        return funcionMapper.toDomain(saved);
    }

    @Override
    public Optional<FuncionCine> buscarPorId(
            String funcionCineId) {

        return jpaFuncionRepository
                .findById(funcionCineId)
                .map(funcionMapper::toDomain);
    }

    @Override
    public List<FuncionCine> buscarFuncionesDisponibles(
            LocalDate fecha) {

        LocalDateTime inicioDia =
                fecha.atStartOfDay();

        LocalDateTime finDia =
                fecha.atTime(23, 59, 59);

        return jpaFuncionRepository
                .findByHorarioInicioBetween(
                        inicioDia,
                        finDia
                )
                .stream()
                .map(funcionMapper::toDomain)
                .toList();
    }
}
