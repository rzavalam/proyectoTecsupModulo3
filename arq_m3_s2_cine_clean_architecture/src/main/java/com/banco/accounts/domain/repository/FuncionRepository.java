package com.banco.accounts.domain.repository;

import com.banco.accounts.domain.model.FuncionCine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FuncionRepository {

    FuncionCine guardar(FuncionCine funcionCine);

    List<FuncionCine> buscarFuncionesDisponibles(LocalDate fecha);

    Optional<FuncionCine> buscarPorId(String funcionId);
}
