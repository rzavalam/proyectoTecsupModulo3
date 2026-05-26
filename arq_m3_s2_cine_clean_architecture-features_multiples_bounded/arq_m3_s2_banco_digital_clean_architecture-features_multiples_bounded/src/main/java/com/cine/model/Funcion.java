package com.cine.model;

import com.cine.model.enums.EstadoFuncion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "funcion_cine")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Funcion {

    @Id
    @Column(name = "funcion_cine_id", length = 50)
    private String idFuncionCine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "tipo_funcion", nullable = false, length = 20)
    private String tipoFuncion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoFuncion estado;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}
