package com.cine.funcionescine.domain.model.vo;

import java.math.BigDecimal;

public final class Precio {

    private final BigDecimal monto;
    private final String moneda;

    public Precio(BigDecimal monto, String moneda) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El monto del precio debe ser positivo");
        if (moneda == null || moneda.isBlank())
            throw new IllegalArgumentException("La moneda es requerida");
        this.monto = monto;
        this.moneda = moneda.toUpperCase();
    }

    public BigDecimal getMonto() { return monto; }
    public String getMoneda() { return moneda; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Precio that)) return false;
        return monto.compareTo(that.monto) == 0 && moneda.equals(that.moneda);
    }

    @Override
    public int hashCode() {
        return 31 * monto.hashCode() + moneda.hashCode();
    }

    @Override
    public String toString() {
        return monto.toPlainString() + " " + moneda;
    }
}
