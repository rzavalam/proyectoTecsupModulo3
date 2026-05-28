package com.cine.funcionescine.domain.ports.outbound;

import com.cine.funcionescine.domain.model.Sala;

import java.util.Optional;

public interface SalaRepositoryPort {

    void guardar(Sala sala);

    Optional<Sala> buscarPorId(String salaId);
}
