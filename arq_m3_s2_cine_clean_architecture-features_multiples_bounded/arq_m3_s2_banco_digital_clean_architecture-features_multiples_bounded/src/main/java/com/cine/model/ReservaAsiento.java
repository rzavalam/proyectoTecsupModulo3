package com.cine.model;

import com.cine.model.enums.EstadoAsiento;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reserva_asiento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaAsiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva_asiento")
    private Integer idReservaAsiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcion", nullable = false)
    private Funcion funcion;

    @Column(nullable = false, length = 5)
    private String fila;

    @Column(nullable = false)
    private Integer numero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoAsiento estado;
}
