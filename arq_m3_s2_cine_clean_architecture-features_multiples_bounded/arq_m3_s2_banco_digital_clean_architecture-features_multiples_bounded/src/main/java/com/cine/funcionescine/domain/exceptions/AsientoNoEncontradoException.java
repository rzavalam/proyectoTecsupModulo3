package com.cine.funcionescine.domain.exceptions;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;

public class AsientoNoEncontradoException extends RuntimeException {

    public AsientoNoEncontradoException(NumeroAsiento numero) {
        super("El asiento " + numero.getCodigo() + " no existe en esta función");
    }
}
