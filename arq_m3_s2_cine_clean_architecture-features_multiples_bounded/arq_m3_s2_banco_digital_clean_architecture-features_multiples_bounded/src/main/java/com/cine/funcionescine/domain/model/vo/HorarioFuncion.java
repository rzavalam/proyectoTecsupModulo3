package com.cine.funcionescine.domain.model.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class HorarioFuncion {

    private final LocalDate fecha;
    private final LocalTime hora;

    public HorarioFuncion(LocalDate fecha, LocalTime hora) {
        if (fecha == null) throw new IllegalArgumentException("La fecha del horario es requerida");
        if (hora == null) throw new IllegalArgumentException("La hora del horario es requerida");
        this.fecha = fecha;
        this.hora = hora;
    }

    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }

    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.of(fecha, hora);
    }

    public boolean esFuturo() {
        return toLocalDateTime().isAfter(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HorarioFuncion that)) return false;
        return fecha.equals(that.fecha) && hora.equals(that.hora);
    }

    @Override
    public int hashCode() {
        return 31 * fecha.hashCode() + hora.hashCode();
    }

    @Override
    public String toString() {
        return fecha + "T" + hora;
    }
}
