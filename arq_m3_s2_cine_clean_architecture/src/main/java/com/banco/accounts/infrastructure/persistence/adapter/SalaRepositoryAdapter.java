package com.banco.accounts.infrastructure.persistence.adapter;

import com.banco.accounts.domain.repository.SalaRepository;
import com.banco.accounts.infrastructure.persistence.repository.JpaSalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalaRepositoryAdapter  implements SalaRepository {

    private final JpaSalaRepository repository;

    @Override
    public boolean existeSala(String salaId) {

        return repository.existsById(salaId);
    }
}
