package com.cine.funcionescine.domain.exceptions;

public class SalaNoEncontradaException extends RuntimeException {

    public SalaNoEncontradaException(String salaId) {
        super("Sala no encontrada con ID: " + salaId);
    }
}
