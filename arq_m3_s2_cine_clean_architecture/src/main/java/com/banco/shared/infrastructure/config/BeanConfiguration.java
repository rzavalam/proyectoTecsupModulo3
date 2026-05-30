package com.banco.shared.infrastructure.config;

import com.banco.accounts.application.usecase.ConsultarFuncionesDisponiblesUseCase;
import com.banco.accounts.application.usecase.CrearFuncionUseCase;
import com.banco.accounts.domain.repository.FuncionRepository;
import com.banco.accounts.domain.repository.PeliculaRepository;
import com.banco.accounts.domain.repository.SalaRepository;
import com.banco.accounts.infrastructure.notification.ConsoleNotificationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * CONFIGURACIÓN DE BEANS
 * 
 * Registra los Use Cases y Domain Services como beans de Spring.
 * 
 * Nota: Lombok @RequiredArgsConstructor se encarga de la inyección,
 * aquí solo creamos las instancias.
 */
@Configuration
@EnableTransactionManagement
public class BeanConfiguration {


    @Bean
    public CrearFuncionUseCase crearFuncionUseCase(
            FuncionRepository funcionRepository,
            ConsoleNotificationAdapter notificationAdapter,
            SalaRepository salaRepository,
            PeliculaRepository peliculaRepository
            ) {

        return new CrearFuncionUseCase(
                funcionRepository,
                notificationAdapter,
                salaRepository,
                peliculaRepository


        );
    }

    @Bean
    public ConsultarFuncionesDisponiblesUseCase
    consultarFuncionesDisponiblesUseCase(
            FuncionRepository funcionRepository) {

        return new ConsultarFuncionesDisponiblesUseCase(
                funcionRepository
        );
    }

    /*@Bean
    public ConsultarAsientosDisponiblesUseCase
    consultarAsientosDisponiblesUseCase(
            FuncionRepository funcionRepository,
            SalaRepository salaRepository) {

        return new ConsultarAsientosDisponiblesUseCase(
                funcionRepository,
                salaRepository
        );
    }*/
}
