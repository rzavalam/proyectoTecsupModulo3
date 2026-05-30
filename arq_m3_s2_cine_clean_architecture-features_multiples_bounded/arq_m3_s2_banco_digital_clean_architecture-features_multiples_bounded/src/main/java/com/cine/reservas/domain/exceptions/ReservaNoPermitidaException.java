package com.cine.reservas.domain.exceptions;

public class ReservaNoPermitidaException extends RuntimeException {
    public ReservaNoPermitidaException(String message) {
        super(message);
    }
}
