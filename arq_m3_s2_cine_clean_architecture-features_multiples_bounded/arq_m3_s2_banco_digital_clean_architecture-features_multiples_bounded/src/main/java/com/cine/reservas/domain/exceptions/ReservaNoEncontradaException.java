package com.cine.reservas.domain.exceptions;

public class ReservaNoEncontradaException extends RuntimeException {
    public ReservaNoEncontradaException(String reservaId) {
        super("Reserva no encontrada con ID: " + reservaId);
    }
}
