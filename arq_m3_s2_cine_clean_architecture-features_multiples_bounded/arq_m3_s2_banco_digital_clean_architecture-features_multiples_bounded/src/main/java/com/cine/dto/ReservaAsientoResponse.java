package com.cine.dto;

import com.cine.model.enums.EstadoAsiento;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaAsientoResponse {

    private Integer idReservaAsiento;
    private ReservaResponse reserva;
    private FuncionResponse funcion;
    private String fila;
    private Integer numero;
    private EstadoAsiento estado;
}
