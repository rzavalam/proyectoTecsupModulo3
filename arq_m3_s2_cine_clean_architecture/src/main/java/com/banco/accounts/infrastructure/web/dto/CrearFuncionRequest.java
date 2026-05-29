package com.banco.accounts.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearFuncionRequest {
    private String salaId;
    private String peliculaId;
    private LocalDateTime horarioInicio;
    private BigDecimal precio;
    private String tipoFuncion;
}
