package com.cine.funcionescine.domain.exceptions;

public class FuncionNoEncontradaException extends RuntimeException {

    public FuncionNoEncontradaException(String funcionId) {
        super("Función no encontrada con ID: " + funcionId);
    }
}
