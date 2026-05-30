package com.cine.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaResponse {

    private String idSala;
    private String nombre;
    private Integer capacidad;
}
