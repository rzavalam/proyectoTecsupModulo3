package com.cine.funcionescine.infrastructure.adapters.outbound;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaSpringRepository extends JpaRepository<SalaJpaEntity, String> {
}
