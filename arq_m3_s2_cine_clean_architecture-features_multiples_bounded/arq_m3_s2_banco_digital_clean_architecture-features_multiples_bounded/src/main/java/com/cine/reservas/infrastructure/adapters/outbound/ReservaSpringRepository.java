package com.cine.reservas.infrastructure.adapters.outbound;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaSpringRepository extends JpaRepository<ReservaJpaEntity, String> {
}
