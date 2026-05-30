package com.cine.reservas.domain.exceptions;

public class CancelacionNoPermitidaException extends RuntimeException {
    public CancelacionNoPermitidaException(String message) {
        super(message);
    }
}
