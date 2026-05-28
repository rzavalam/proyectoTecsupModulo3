package com.cine.dto;

import com.cine.model.enums.EstadoCliente;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {

    private Integer idCliente;
    private String nombre;
    private String correo;
    private EstadoCliente estado;
}
