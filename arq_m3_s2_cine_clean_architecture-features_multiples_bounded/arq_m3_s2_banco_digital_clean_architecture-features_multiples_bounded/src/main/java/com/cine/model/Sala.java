package com.cine.model;

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
    @Column(name = "sala_id", length = 50)
    private String idSala;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private Integer capacidad;
}
