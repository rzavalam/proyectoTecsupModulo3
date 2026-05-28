package com.cine.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaRequest {

    private String idPelicula;
    private String tituloPelicula;
    private BigDecimal duracion;
    private String clasificacion;
}
