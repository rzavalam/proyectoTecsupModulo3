package com.cine.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pelicula")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {

    @Id
    @Column(name = "pelicula_id", length = 50)
    private String idPelicula;

    @Column(name = "titulo_pelicula", nullable = false, unique = true, length = 100)
    private String tituloPelicula;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal duracion;

    @Column(nullable = false, length = 20)
    private String clasificacion;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}
