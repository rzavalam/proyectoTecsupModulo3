package com.banco.accounts.application.usecase;

import com.banco.accounts.application.dto.CrearFuncionCommand;
import com.banco.accounts.domain.exception.HorarioFuncionInvalidoException;
import com.banco.accounts.domain.exception.PeliculaNoEncontradaException;
import com.banco.accounts.domain.exception.SalaNoEncontradaException;
import com.banco.accounts.domain.model.EstadoFuncion;
import com.banco.accounts.domain.model.FuncionCine;
import com.banco.accounts.domain.repository.FuncionRepository;
import com.banco.accounts.domain.repository.PeliculaRepository;
import com.banco.accounts.domain.repository.SalaRepository;
import com.banco.accounts.domain.services.FuncionDomainService;
import com.banco.accounts.infrastructure.notification.ConsoleNotificationAdapter;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@RequiredArgsConstructor
@Transactional
public class CrearFuncionUseCase {

    private final FuncionRepository funcionRepository;
    private final ConsoleNotificationAdapter notificationAdapter;
    private final FuncionDomainService funcionDomainService;

    public FuncionCine ejecutar(CrearFuncionCommand command) {



        FuncionCine funcion = new FuncionCine(
                command.getSalaId(),
                command.getPeliculaId(),
                command.getHorarioInicio(),
                command.getPrecio(),
                command.getTipoFuncion()
        );

        funcionDomainService.validarSala(
                command.getSalaId());

        funcionDomainService.validarPelicula(
                command.getPeliculaId());

        funcionDomainService.validarHorario(
                command.getHorarioInicio());
        
        FuncionCine saved = funcionRepository.guardar(funcion);

        notificationAdapter.notificarFuncionCreada(
                saved.getFuncionCineId(),
                command
        );

        return saved;
    }


}
