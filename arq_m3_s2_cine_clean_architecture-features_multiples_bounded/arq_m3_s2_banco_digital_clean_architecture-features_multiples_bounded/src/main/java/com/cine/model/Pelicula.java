package com.cine.model;

import com.cine.model.enums.EstadoPelicula;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pelicula")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private Integer idPelicula;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(name = "duracion_minutos", nullable = false)
    private Integer duracionMinutos;

    @Column(nullable = false, length = 10)
    private String clasificacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPelicula estado;
}
