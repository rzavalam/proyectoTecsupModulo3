package com.banco.accounts.infrastructure.persistence.repository;

import com.banco.accounts.infrastructure.persistence.entity.FuncionCineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface  JpaFuncionRepository
        extends JpaRepository<FuncionCineEntity, String> {

    List<FuncionCineEntity> findByHorarioInicioBetween(
            LocalDateTime inicio,
            LocalDateTime fin
    );
}
