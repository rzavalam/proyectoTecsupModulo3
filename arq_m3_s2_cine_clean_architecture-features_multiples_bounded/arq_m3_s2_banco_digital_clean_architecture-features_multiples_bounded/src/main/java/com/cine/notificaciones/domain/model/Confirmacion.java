package com.cine.notificaciones.domain.model;

import java.time.LocalDateTime;

/** Representa la confirmación o cancelación enviada al cliente. */
public class Confirmacion {

    private final String reservaId;
    private final String clienteId;
    private final TipoNotificacion tipo;
    private final LocalDateTime enviadoEn;
    private final String mensaje;

    public Confirmacion(String reservaId, String clienteId, TipoNotificacion tipo, String mensaje) {
        this.reservaId = reservaId;
        this.clienteId = clienteId;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.enviadoEn = LocalDateTime.now();
    }

    public String getReservaId() { return reservaId; }
    public String getClienteId() { return clienteId; }
    public TipoNotificacion getTipo() { return tipo; }
    public String getMensaje() { return mensaje; }
    public LocalDateTime getEnviadoEn() { return enviadoEn; }

    public enum TipoNotificacion {
        CONFIRMACION, CANCELACION
    }
}
