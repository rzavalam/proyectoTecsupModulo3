package com.cine.dto;

import com.cine.model.enums.EstadoAsiento;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaAsientoRequest {

    private Integer idReserva;
    private String idFuncionCine;
    private String fila;
    private Integer numero;
    private EstadoAsiento estado;
}
