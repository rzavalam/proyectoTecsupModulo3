package com.cine.reservas.domain.model;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;

/** Value Object que representa un asiento dentro de una Reserva. */
public final class AsientoReservado {

    private final NumeroAsiento numero;

    public AsientoReservado(NumeroAsiento numero) {
        if (numero == null) throw new IllegalArgumentException("El número de asiento es requerido");
        this.numero = numero;
    }

    public NumeroAsiento getNumero() { return numero; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AsientoReservado that)) return false;
        return numero.equals(that.numero);
    }

    @Override
    public int hashCode() { return numero.hashCode(); }

    @Override
    public String toString() { return "AsientoReservado{" + numero + "}"; }
}
