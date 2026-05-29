package com.banco.accounts.domain.exception;

public class SalaNoEncontradaException extends RuntimeException {

    public SalaNoEncontradaException(String salaId) {

        super("La sala no existe: " + salaId);
    }
}