package com.cine.model;

import com.cine.model.enums.EstadoCliente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoCliente estado;
}
