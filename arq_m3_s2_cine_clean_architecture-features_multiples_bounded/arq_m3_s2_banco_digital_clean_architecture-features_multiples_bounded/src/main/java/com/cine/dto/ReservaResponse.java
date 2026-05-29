package com.cine.dto;

import com.cine.model.enums.EstadoReserva;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponse {

    private Integer idReserva;
    private ClienteResponse cliente;
    private FuncionResponse funcion;
    private LocalDateTime fechaReserva;
    private Integer cantidadAsientos;
    private EstadoReserva estado;
}
