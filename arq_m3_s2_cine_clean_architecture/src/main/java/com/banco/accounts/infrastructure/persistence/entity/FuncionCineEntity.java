package com.banco.accounts.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "funcion_cine")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionCineEntity {

    @Id
    @Column(name = "funcion_cine_id")
    private String funcionCineId;

    @Column(name = "sala_id")
    private String salaId;

    @Column(name = "pelicula_id")
    private String peliculaId;

    @Column(name = "horario_inicio")
    private LocalDateTime horarioInicio;

    @Column(name = "precio")
    private BigDecimal precio;

    @Column(name = "tipo_funcion")
    private String tipoFuncion;

    @Column(name = "estado")
    private String estado;
}
