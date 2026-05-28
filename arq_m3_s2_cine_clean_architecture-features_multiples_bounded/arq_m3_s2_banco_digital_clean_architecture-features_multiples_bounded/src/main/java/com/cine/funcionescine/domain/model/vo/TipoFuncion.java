package com.cine.funcionescine.domain.model.vo;

public enum TipoFuncion {
    ESTANDAR, TRES_D, VIP;

    public boolean tieneRecargo() {
        return this == TRES_D || this == VIP;
    }
}
