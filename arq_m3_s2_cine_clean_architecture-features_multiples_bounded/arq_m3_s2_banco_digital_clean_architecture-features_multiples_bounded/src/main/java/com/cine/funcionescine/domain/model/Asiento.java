package com.cine.funcionescine.domain.model;

import com.cine.funcionescine.domain.model.vo.EstadoAsiento;
import com.cine.funcionescine.domain.model.vo.NumeroAsiento;

import java.util.ArrayList;
import java.util.List;

public class Asiento {

    private final NumeroAsiento numero;
    private EstadoAsiento estado;

    public Asiento(NumeroAsiento numero) {
        this.numero = numero;
        this.estado = EstadoAsiento.DISPONIBLE;
    }

    public Asiento(NumeroAsiento numero, EstadoAsiento estado) {
        this.numero = numero;
        this.estado = estado;
    }

    public NumeroAsiento getNumero() { return numero; }
    public EstadoAsiento getEstado() { return estado; }

    public boolean estaDisponible() {
        return estado == EstadoAsiento.DISPONIBLE;
    }

    public void reservar() {
        this.estado = EstadoAsiento.RESERVADO;
    }

    public void liberar() {
        this.estado = EstadoAsiento.DISPONIBLE;
    }

    /** Genera asientos para una sala según su capacidad: filas A-Z, números 1-20. */
    public static List<Asiento> generarParaSala(int capacidad) {
        List<Asiento> asientos = new ArrayList<>();
        int generados = 0;
        for (char fila = 'A'; fila <= 'Z' && generados < capacidad; fila++) {
            for (int num = 1; num <= 20 && generados < capacidad; num++) {
                asientos.add(new Asiento(new NumeroAsiento(fila, num)));
                generados++;
            }
        }
        return asientos;
    }
}
