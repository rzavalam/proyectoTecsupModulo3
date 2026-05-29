package com.banco.accounts.infrastructure.persistence.repository;

import com.banco.accounts.infrastructure.persistence.entity.SalaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSalaRepository
        extends JpaRepository<SalaEntity, String> {
}
