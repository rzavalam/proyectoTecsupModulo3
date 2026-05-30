package com.banco.accounts.domain.exception;

public class HorarioFuncionInvalidoException extends RuntimeException {

    public HorarioFuncionInvalidoException() {

        super("La función no puede programarse en el pasado");
    }
}
