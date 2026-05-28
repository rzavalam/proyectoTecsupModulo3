package com.cine.clientes.infrastructure.adapters.outbound;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteSpringRepository extends JpaRepository<ClienteJpaEntity, String> {
}
