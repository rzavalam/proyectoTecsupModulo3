package com.banco.accounts.application.usecase;

import com.banco.accounts.domain.model.EstadoFuncion;
import com.banco.accounts.domain.model.FuncionCine;
import com.banco.accounts.domain.repository.FuncionRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
public class ConsultarFuncionesDisponiblesUseCase {

    private final FuncionRepository funcionRepository;

    public List<FuncionCine> ejecutar(LocalDate fecha) {

        validateFecha(fecha);

        return funcionRepository
                .buscarFuncionesDisponibles(fecha)
                .stream()
                .filter(funcion ->
                        funcion.getEstado() == EstadoFuncion.ACTIVA)
                .toList();
    }

    private void validateFecha(LocalDate fecha) {

        if (fecha == null) {
            throw new IllegalArgumentException(
                    "La fecha es obligatoria"
            );
        }
    }
}
