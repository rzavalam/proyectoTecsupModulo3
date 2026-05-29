package com.cine.reservas.infrastructure.adapters.outbound;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asientos_reservados")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsientoReservadoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1)
    private char fila;

    @Column(nullable = false)
    private int numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id", nullable = false)
    private ReservaJpaEntity reserva;
}
