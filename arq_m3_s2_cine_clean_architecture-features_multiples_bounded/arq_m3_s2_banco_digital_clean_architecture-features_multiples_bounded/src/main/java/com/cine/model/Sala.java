package com.cine.model;

import com.cine.model.enums.EstadoSala;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sala")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private Integer idSala;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private Integer capacidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoSala estado;
}
