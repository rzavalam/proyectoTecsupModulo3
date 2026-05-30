package com.cine.clientes.domain.events;

import com.cine.shared.domain.DomainEvent;

public class ClienteRegistrado extends DomainEvent {

    private final String clienteId;
    private final String nombre;
    private final String correo;

    public ClienteRegistrado(String clienteId, String nombre, String correo) {
        super();
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getClienteId() { return clienteId; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }

    @Override
    public String getEventType() { return "ClienteRegistrado"; }
}
