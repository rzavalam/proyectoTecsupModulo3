package com.cine.dto;

import com.cine.model.enums.EstadoFuncion;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionRequest {

    private String idFuncionCine;
    private String idSala;
    private String idPelicula;
    private BigDecimal precio;
    private String tipoFuncion;
    private EstadoFuncion estado;
}
