package com.banco.accounts.domain.exception;

import com.banco.shared.domain.exception.DomainException;

public class FuncionNotFoundException extends DomainException {

    public FuncionNotFoundException(String message) {
        super(message);
    }
}
