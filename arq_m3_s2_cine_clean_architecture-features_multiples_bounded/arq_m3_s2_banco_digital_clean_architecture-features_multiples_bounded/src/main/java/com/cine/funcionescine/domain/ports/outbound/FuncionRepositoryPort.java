package com.cine.funcionescine.domain.ports.outbound;

import com.cine.funcionescine.domain.model.Funcion;

import java.util.Optional;

public interface FuncionRepositoryPort {

    void guardar(Funcion funcion);

    Optional<Funcion> buscarPorId(String funcionId);
}
