package com.banco.accounts.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AsientoDisponibleResponse {
    private String codigoAsiento;
    private boolean disponible;
}
