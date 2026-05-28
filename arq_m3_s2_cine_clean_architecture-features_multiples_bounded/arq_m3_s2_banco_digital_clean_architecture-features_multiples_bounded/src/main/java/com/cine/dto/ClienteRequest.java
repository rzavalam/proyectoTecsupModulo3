package com.cine.dto;

import com.cine.model.enums.EstadoCliente;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    private String nombre;
    private String correo;
    private EstadoCliente estado;
}
