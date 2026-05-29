package com.banco.accounts.infrastructure.notification;

import com.banco.accounts.application.dto.CrearFuncionCommand;
import com.banco.accounts.domain.model.FuncionCine;
import com.banco.accounts.domain.repository.FuncionRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * CAPA 4 - INFRASTRUCTURE / Notification
 * Implementación del puerto de notificación vía consola.
 * Puede reemplazarse por email, SMS, etc. sin tocar el dominio.
 */
@Slf4j
@Component
public class ConsoleNotificationAdapter {

    /**
     * Notificación cuando una función es creada
     */
    public void notificarFuncionCreada(
            String funcionId,
            CrearFuncionCommand command) {

        log.info("🎬 FUNCIÓN CREADA");
        log.info("   ID Función     : {}", funcionId);
        log.info("   Sala           : {}", command.getSalaId());
        log.info("   Película       : {}", command.getPeliculaId());
        log.info("   Horario Inicio : {}", command.getHorarioInicio());
        log.info("   Precio         : S/ {}", command.getPrecio());
        log.info("   Tipo Función   : {}", command.getTipoFuncion());
    }

    /**
     * Notificación cuando una función es cancelada
     */
    public void notificarFuncionCancelada(
            String funcionId) {

        log.info("❌ FUNCIÓN CANCELADA");
        log.info("   ID Función : {}", funcionId);
    }

    /**
     * Notificación cuando una función finaliza
     */
    public void notificarFuncionFinalizada(
            String funcionId) {

        log.info("✅ FUNCIÓN FINALIZADA");
        log.info("   ID Función : {}", funcionId);
    }
}
