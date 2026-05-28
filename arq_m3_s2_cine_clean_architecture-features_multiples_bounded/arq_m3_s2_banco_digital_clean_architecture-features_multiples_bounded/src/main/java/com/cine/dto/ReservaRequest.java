package com.cine.dto;

import com.cine.model.enums.EstadoReserva;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequest {

    private Integer idCliente;
    private String idFuncionCine;
    private LocalDateTime fechaReserva;
    private Integer cantidadAsientos;
    private EstadoReserva estado;
}
