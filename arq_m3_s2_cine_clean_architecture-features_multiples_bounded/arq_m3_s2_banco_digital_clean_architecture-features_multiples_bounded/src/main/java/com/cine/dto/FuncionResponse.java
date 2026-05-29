package com.cine.dto;

import com.cine.model.enums.EstadoFuncion;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionResponse {

    private String idFuncionCine;
    private SalaResponse sala;
    private PeliculaResponse pelicula;
    private BigDecimal precio;
    private String tipoFuncion;
    private EstadoFuncion estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
