package com.banco.accounts.infrastructure.persistence.adapter;

import com.banco.accounts.domain.repository.PeliculaRepository;
import com.banco.accounts.infrastructure.persistence.repository.JpaPeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PeliculaRepositoryAdapter   implements PeliculaRepository {

    private final JpaPeliculaRepository repository;

    @Override
    public boolean existePelicula(String peliculaId) {

        return repository.existsById(peliculaId);
    }
}
