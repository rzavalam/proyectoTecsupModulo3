package com.cine.funcionescine.domain.exceptions;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;

public class AsientoOcupadoException extends RuntimeException {

    public AsientoOcupadoException(NumeroAsiento numero) {
        super("El asiento " + numero.getCodigo() + " ya está reservado en esta función");
    }
}
