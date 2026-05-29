package com.cine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cine")
@EntityScan(basePackages = {
        "com.cine.funcionescine.infrastructure.adapters.outbound",
        "com.cine.reservas.infrastructure.adapters.outbound",
        "com.cine.clientes.infrastructure.adapters.outbound"
})
@EnableJpaRepositories(basePackages = {
        "com.cine.funcionescine.infrastructure.adapters.outbound",
        "com.cine.reservas.infrastructure.adapters.outbound",
        "com.cine.clientes.infrastructure.adapters.outbound"
})
public class CineApplication {
    public static void main(String[] args) {
        SpringApplication.run(CineApplication.class, args);
    }
}
