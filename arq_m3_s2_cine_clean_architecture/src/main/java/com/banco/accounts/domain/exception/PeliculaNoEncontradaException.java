package com.banco.accounts.domain.exception;

public class PeliculaNoEncontradaException extends RuntimeException {

    public PeliculaNoEncontradaException(String peliculaId) {

        super("La película no existe: " + peliculaId);
    }
}