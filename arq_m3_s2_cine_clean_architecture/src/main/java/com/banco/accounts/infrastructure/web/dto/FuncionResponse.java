package com.banco.accounts.infrastructure.web.dto;

import com.banco.accounts.domain.model.FuncionCine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionResponse {
    private String funcionCineId;
    private String salaId;
    private String peliculaId;
    private LocalDateTime horarioInicio;
    private BigDecimal precio;
    private String tipoFuncion;
    private String estado;

    /**
     * Factory method para crear desde FuncionCine
     */
    public static FuncionResponse from(FuncionCine funcionCine) {

        return FuncionResponse.builder()
                .funcionCineId(funcionCine.getFuncionCineId())
                .salaId(funcionCine.getSalaId())
                .peliculaId(funcionCine.getPeliculaId())
                .horarioInicio(funcionCine.getHorarioInicio())
                .precio(funcionCine.getPrecio())
                .tipoFuncion(funcionCine.getTipoFuncion().name())
                .estado(funcionCine.getEstado().name())
                .build();
    }
}
