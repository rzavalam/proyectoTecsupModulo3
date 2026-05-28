package com.cine.funcionescine.domain.model;

/** Entidad de dominio. Representa una sala de cine con capacidad fija (invariante 5). */
public class Sala {

    private final String id;
    private final String nombre;
    private final int capacidad;

    public Sala(String id, String nombre, int capacidad) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("El ID de sala es requerido");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre de sala es requerido");
        if (capacidad <= 0 || capacidad > 520)
            throw new IllegalArgumentException("La capacidad debe estar entre 1 y 520 asientos");
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
}
