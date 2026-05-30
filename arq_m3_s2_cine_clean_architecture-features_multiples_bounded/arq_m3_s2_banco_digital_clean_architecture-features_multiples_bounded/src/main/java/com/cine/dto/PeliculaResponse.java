package com.cine.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaResponse {

    private String idPelicula;
    private String tituloPelicula;
    private BigDecimal duracion;
    private String clasificacion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
