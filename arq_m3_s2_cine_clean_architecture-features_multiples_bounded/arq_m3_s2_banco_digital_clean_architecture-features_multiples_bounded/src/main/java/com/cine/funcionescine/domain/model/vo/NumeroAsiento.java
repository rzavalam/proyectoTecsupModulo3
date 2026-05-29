package com.cine.funcionescine.domain.model.vo;

public final class NumeroAsiento {

    private final char fila;
    private final int numero;

    public NumeroAsiento(char fila, int numero) {
        if (fila < 'A' || fila > 'Z')
            throw new IllegalArgumentException("La fila debe estar entre A y Z, recibido: " + fila);
        if (numero < 1 || numero > 20)
            throw new IllegalArgumentException("El número de asiento debe estar entre 1 y 20, recibido: " + numero);
        this.fila = fila;
        this.numero = numero;
    }

    public char getFila() { return fila; }
    public int getNumero() { return numero; }

    public String getCodigo() {
        return String.valueOf(fila) + numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumeroAsiento that)) return false;
        return fila == that.fila && numero == that.numero;
    }

    @Override
    public int hashCode() {
        return 31 * fila + numero;
    }

    @Override
    public String toString() {
        return getCodigo();
    }
}
