package com.banco.accounts.infrastructure.persistence.repository;

import com.banco.accounts.infrastructure.persistence.entity.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPeliculaRepository
        extends JpaRepository<PeliculaEntity, String> {
}