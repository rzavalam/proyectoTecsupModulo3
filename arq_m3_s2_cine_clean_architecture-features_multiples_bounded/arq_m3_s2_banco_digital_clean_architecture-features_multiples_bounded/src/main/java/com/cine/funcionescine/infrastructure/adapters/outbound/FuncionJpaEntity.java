package com.cine.funcionescine.infrastructure.adapters.outbound;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funciones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionJpaEntity {

    @Id
    @Column(length = 50)
    private String id;

    @Column(name = "pelicula_id", nullable = false, length = 50)
    private String peliculaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id", nullable = false)
    private SalaJpaEntity sala;

    @Column(name = "horario_fecha", nullable = false)
    private LocalDate horarioFecha;

    @Column(name = "horario_hora", nullable = false)
    private LocalTime horarioHora;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false, length = 5)
    private String moneda;

    @Column(name = "tipo_funcion", nullable = false, length = 15)
    private String tipoFuncion;

    @OneToMany(mappedBy = "funcion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<AsientoJpaEntity> asientos = new ArrayList<>();
}
