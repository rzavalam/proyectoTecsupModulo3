package com.cine.clientes.domain.model;

import com.cine.clientes.domain.events.ClienteRegistrado;
import com.cine.shared.domain.DomainEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Agregado raíz del Bounded Context GestiónDeClientes.
 * Relación: Conformist → GestiónDeReservas usa el clienteId tal como lo define este BC.
 */
public class Cliente {

    private final String id;
    private final String nombre;
    private final String correo;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private Cliente(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public static Cliente registrar(String id, String nombre, String correo) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("El ID del cliente es requerido");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre del cliente es requerido");
        if (correo == null || !correo.contains("@"))
            throw new IllegalArgumentException("El correo del cliente es inválido");

        Cliente cliente = new Cliente(id, nombre, correo);
        cliente.domainEvents.add(new ClienteRegistrado(id, nombre, correo));
        return cliente;
    }

    public static Cliente reconstituir(String id, String nombre, String correo) {
        return new Cliente(id, nombre, correo);
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
}
