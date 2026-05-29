package com.banco.accounts.application.dto;

import com.banco.accounts.domain.model.TipoFuncion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CrearFuncionCommand {
    private String salaId;
    private String peliculaId;
    private LocalDateTime horarioInicio;
    private BigDecimal precio;
    private TipoFuncion tipoFuncion;
}
