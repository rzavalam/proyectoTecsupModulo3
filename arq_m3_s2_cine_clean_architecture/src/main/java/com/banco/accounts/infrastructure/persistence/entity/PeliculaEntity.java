package com.banco.accounts.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "pelicula")
@Getter
@Setter
public class PeliculaEntity {

    @Id
    @Column(name = "pelicula_id")
    private String peliculaId;

    @Column(name = "titulo_pelicula")
    private String tituloPelicula;

    @Column(name = "duracion")
    private BigDecimal duracion;

    @Column(name = "clasificacion")
    private String clasificacion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void prePersist() {

        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {

        this.fechaActualizacion = LocalDateTime.now();
    }
}
