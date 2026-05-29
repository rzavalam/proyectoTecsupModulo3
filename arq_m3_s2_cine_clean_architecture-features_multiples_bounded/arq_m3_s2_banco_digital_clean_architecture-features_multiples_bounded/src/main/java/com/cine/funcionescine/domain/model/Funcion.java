package com.cine.funcionescine.domain.model;

import com.cine.funcionescine.domain.events.FuncionCreada;
import com.cine.funcionescine.domain.exceptions.AsientoOcupadoException;
import com.cine.funcionescine.domain.exceptions.AsientoNoEncontradoException;
import com.cine.funcionescine.domain.model.vo.*;
import com.cine.shared.domain.DomainEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Agregado raíz del Bounded Context GestiónDeFunciones.
 * Gestiona la disponibilidad de asientos (invariante 1) y la capacidad fija de sala (invariante 5).
 */
public class Funcion {

    private final String id;
    private final String peliculaId;
    private final Sala sala;
    private final HorarioFuncion horario;
    private final Precio precio;
    private final TipoFuncion tipoFuncion;
    private final List<Asiento> asientos;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private Funcion(String id, String peliculaId, Sala sala, HorarioFuncion horario,
                    Precio precio, TipoFuncion tipoFuncion, List<Asiento> asientos) {
        this.id = id;
        this.peliculaId = peliculaId;
        this.sala = sala;
        this.horario = horario;
        this.precio = precio;
        this.tipoFuncion = tipoFuncion;
        this.asientos = new ArrayList<>(asientos);
    }

    /** Crea una nueva función generando los asientos según la capacidad de la sala. */
    public static Funcion crear(String id, String peliculaId, Sala sala,
                                 HorarioFuncion horario, Precio precio, TipoFuncion tipoFuncion) {
        if (!horario.esFuturo())
            throw new IllegalArgumentException("El horario de la función debe ser en el futuro");

        List<Asiento> asientos = Asiento.generarParaSala(sala.getCapacidad());
        Funcion funcion = new Funcion(id, peliculaId, sala, horario, precio, tipoFuncion, asientos);
        funcion.domainEvents.add(new FuncionCreada(id, peliculaId, sala.getId(), horario, tipoFuncion, precio));
        return funcion;
    }

    /** Reconstituye el agregado desde persistencia sin emitir eventos. */
    public static Funcion reconstituir(String id, String peliculaId, Sala sala,
                                        HorarioFuncion horario, Precio precio,
                                        TipoFuncion tipoFuncion, List<Asiento> asientos) {
        return new Funcion(id, peliculaId, sala, horario, precio, tipoFuncion, asientos);
    }

    /**
     * Reserva un asiento de esta función.
     * Invariante 1: un asiento no puede ser reservado por más de una persona.
     */
    public void reservarAsiento(NumeroAsiento numero) {
        Asiento asiento = encontrarAsiento(numero);
        if (!asiento.estaDisponible()) {
            throw new AsientoOcupadoException(numero);
        }
        asiento.reservar();
    }

    /** Libera un asiento previamente reservado (usado al cancelar reserva). */
    public void liberarAsiento(NumeroAsiento numero) {
        encontrarAsiento(numero).liberar();
    }

    public boolean tieneAsientosDisponibles() {
        return asientos.stream().anyMatch(Asiento::estaDisponible);
    }

    public long contarAsientosDisponibles() {
        return asientos.stream().filter(Asiento::estaDisponible).count();
    }

    /** Extrae y limpia los eventos de dominio acumulados (patrón pull). */
    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }

    public String getId() { return id; }
    public String getPeliculaId() { return peliculaId; }
    public Sala getSala() { return sala; }
    public HorarioFuncion getHorario() { return horario; }
    public Precio getPrecio() { return precio; }
    public TipoFuncion getTipoFuncion() { return tipoFuncion; }
    public List<Asiento> getAsientos() { return Collections.unmodifiableList(asientos); }

    private Asiento encontrarAsiento(NumeroAsiento numero) {
        return asientos.stream()
                .filter(a -> a.getNumero().equals(numero))
                .findFirst()
                .orElseThrow(() -> new AsientoNoEncontradoException(numero));
    }
}
