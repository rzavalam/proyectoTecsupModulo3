package com.cine.model;

import com.cine.model.enums.EstadoFuncion;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "funcion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcion")
    private Integer idFuncion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala", nullable = false)
    private Sala sala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pelicula", nullable = false)
    private Pelicula pelicula;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "tipo_funcion", nullable = false, length = 50)
    private String tipoFuncion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoFuncion estado;
}
