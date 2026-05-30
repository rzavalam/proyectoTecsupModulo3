package com.banco.accounts.domain.services;

import com.banco.accounts.domain.exception.HorarioFuncionInvalidoException;
import com.banco.accounts.domain.exception.PeliculaNoEncontradaException;
import com.banco.accounts.domain.exception.SalaNoEncontradaException;
import com.banco.accounts.domain.repository.PeliculaRepository;
import com.banco.accounts.domain.repository.SalaRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class FuncionDomainService {

    private final SalaRepository salaRepository;
    private final PeliculaRepository peliculaRepository;

    public void validarSala(String salaId) {

        if (!salaRepository.existeSala(salaId)) {
            throw new SalaNoEncontradaException(salaId);
        }
    }

    public void validarPelicula(String peliculaId) {

        if (!peliculaRepository.existePelicula(peliculaId)) {
            throw new PeliculaNoEncontradaException(peliculaId);
        }
    }

    public void validarHorario(
            LocalDateTime horarioInicio) {

        if (horarioInicio.isBefore(LocalDateTime.now())) {
            throw new HorarioFuncionInvalidoException();
        }
    }
}
