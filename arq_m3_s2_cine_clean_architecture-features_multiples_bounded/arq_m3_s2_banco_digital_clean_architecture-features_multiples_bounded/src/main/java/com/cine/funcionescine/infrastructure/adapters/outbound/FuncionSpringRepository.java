package com.cine.funcionescine.infrastructure.adapters.outbound;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionSpringRepository extends JpaRepository<FuncionJpaEntity, String> {
}
