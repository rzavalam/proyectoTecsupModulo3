package com.banco.accounts.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class FuncionCine {
    @Setter(AccessLevel.PUBLIC) // Solo para JPA
    private String funcionCineId;

    private String salaId;
    private String peliculaId;
    private LocalDateTime horarioInicio;
    private BigDecimal precio;
    private TipoFuncion tipoFuncion;
    private EstadoFuncion estado;

    // Constructor para crear nueva función
    public FuncionCine(
            String salaId,
            String peliculaId,
            LocalDateTime horarioInicio,
            BigDecimal precio,
            TipoFuncion tipoFuncion) {

        validateSalaId(salaId);
        validatePeliculaId(peliculaId);
        validateHorarioInicio(horarioInicio);
        validatePrecio(precio);
        validateTipoFuncion(tipoFuncion);

        this.funcionCineId = UUID.randomUUID().toString();
        this.salaId = salaId;
        this.peliculaId = peliculaId;
        this.horarioInicio = horarioInicio;
        this.precio = precio;
        this.tipoFuncion = tipoFuncion;
        this.estado = EstadoFuncion.ACTIVA;
    }

    // Constructor para reconstruir desde BD (MapStruct/JPA)
    public FuncionCine(
            String funcionCineId,
            String salaId,
            String peliculaId,
            LocalDateTime horarioInicio,
            BigDecimal precio,
            TipoFuncion tipoFuncion,
            EstadoFuncion estado) {

        this.funcionCineId = funcionCineId;
        this.salaId = salaId;
        this.peliculaId = peliculaId;
        this.horarioInicio = horarioInicio;
        this.precio = precio;
        this.tipoFuncion = tipoFuncion;
        this.estado = estado;
    }

    // Constructor protegido para JPA
    protected FuncionCine() {
    }

    // =========================
    // VALIDACIONES
    // =========================

    private void validateSalaId(String salaId) {

        if (salaId == null || salaId.isBlank()) {
            throw new IllegalArgumentException(
                    "El id de la sala es obligatorio"
            );
        }
    }

    private void validatePeliculaId(String peliculaId) {

        if (peliculaId == null || peliculaId.isBlank()) {
            throw new IllegalArgumentException(
                    "El id de la película es obligatorio"
            );
        }
    }

    private void validateHorarioInicio(LocalDateTime horarioInicio) {

        if (horarioInicio == null) {
            throw new IllegalArgumentException(
                    "El horario es obligatorio"
            );
        }

        if (horarioInicio.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "El horario no puede ser menor a la fecha actual"
            );
        }
    }

    private void validatePrecio(BigDecimal precio) {

        if (precio == null) {
            throw new IllegalArgumentException(
                    "El precio es obligatorio"
            );
        }

        if (precio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "El precio debe ser mayor a cero"
            );
        }
    }

    private void validateTipoFuncion(TipoFuncion tipoFuncion) {

        if (tipoFuncion == null) {
            throw new IllegalArgumentException(
                    "El tipo de función es obligatorio"
            );
        }
    }

    // =========================
    // MÉTODOS DE NEGOCIO
    // =========================

    public void cancelarFuncion() {

        validateFuncionCancelable();

        this.estado = EstadoFuncion.CANCELADA;
    }

    public void finalizarFuncion() {

        this.estado = EstadoFuncion.FINALIZADA;
    }

    private void validateFuncionCancelable() {

        if (this.estado != EstadoFuncion.ACTIVA) {
            throw new IllegalStateException(
                    "Solo funciones activas pueden cancelarse"
            );
        }

        if (horarioInicio.minusHours(1).isBefore(LocalDateTime.now())) {
            throw new IllegalStateException(
                    "La función no puede cancelarse 1 hora antes"
            );
        }
    }
}
