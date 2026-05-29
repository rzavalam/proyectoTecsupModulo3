package com.banco.accounts.application.port.output;

import com.banco.accounts.application.dto.CrearFuncionCommand;

public interface NotificationPort {

    /**
     * Notificación cuando una función es creada
     */
    void notificarFuncionCreada(
            String funcionId,
            CrearFuncionCommand command
    );

    /**
     * Notificación cuando una función es cancelada
     */
    void notificarFuncionCancelada(
            String funcionId
    );

    /**
     * Notificación cuando una función finaliza
     */
    void notificarFuncionFinalizada(
            String funcionId
    );
}
