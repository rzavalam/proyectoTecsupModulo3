package com.cine.funcionescine.infrastructure.adapters.outbound;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asientos_funcion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsientoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1)
    private char fila;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false, length = 15)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcion_id", nullable = false)
    private FuncionJpaEntity funcion;
}
