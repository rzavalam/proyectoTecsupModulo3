package com.cine.reservas.domain.ports.outbound;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;

import java.time.LocalDateTime;

/**
 * Puerto de salida: permite al BC de Reservas consultar y modificar
 * la disponibilidad de asientos en el BC de Funciones.
 * Customer/Supplier: GestiónDeFunciones (Supplier) → GestiónDeReservas (Customer).
 */
public interface FuncionQueryPort {

    LocalDateTime obtenerInicioFuncion(String funcionId);

    void reservarAsiento(String funcionId, NumeroAsiento asiento);

    void liberarAsiento(String funcionId, NumeroAsiento asiento);
}
