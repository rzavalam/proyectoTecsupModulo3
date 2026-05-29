package com.cine.reservas.infrastructure.adapters.outbound;

import com.cine.funcionescine.domain.model.vo.EstadoAsiento;
import com.cine.funcionescine.domain.model.vo.NumeroAsiento;
import com.cine.funcionescine.infrastructure.adapters.outbound.AsientoJpaEntity;
import com.cine.funcionescine.infrastructure.adapters.outbound.FuncionJpaEntity;
import com.cine.funcionescine.infrastructure.adapters.outbound.FuncionSpringRepository;
import com.cine.reservas.domain.ports.outbound.FuncionQueryPort;
import com.cine.funcionescine.domain.exceptions.FuncionNoEncontradaException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Adaptador de salida del BC Reservas hacia el BC Funciones.
 * En arquitectura de monolito, accede directamente a la capa de persistencia de Funciones.
 * Relación: Customer/Supplier — Reservas es Customer, Funciones es Supplier.
 */
@Component
public class FuncionQueryAdapter implements FuncionQueryPort {

    private final FuncionSpringRepository funcionRepo;

    public FuncionQueryAdapter(FuncionSpringRepository funcionRepo) {
        this.funcionRepo = funcionRepo;
    }

    @Override
    public LocalDateTime obtenerInicioFuncion(String funcionId) {
        return funcionRepo.findById(funcionId)
                .map(f -> LocalDateTime.of(f.getHorarioFecha(), f.getHorarioHora()))
                .orElseThrow(() -> new FuncionNoEncontradaException(funcionId));
    }

    @Override
    public void reservarAsiento(String funcionId, NumeroAsiento asiento) {
        FuncionJpaEntity funcion = funcionRepo.findById(funcionId)
                .orElseThrow(() -> new FuncionNoEncontradaException(funcionId));

        AsientoJpaEntity asientoEntity = funcion.getAsientos().stream()
                .filter(a -> a.getFila() == asiento.getFila() && a.getNumero() == asiento.getNumero())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Asiento no encontrado: " + asiento));

        asientoEntity.setEstado(EstadoAsiento.RESERVADO.name());
        funcionRepo.save(funcion);
    }

    @Override
    public void liberarAsiento(String funcionId, NumeroAsiento asiento) {
        FuncionJpaEntity funcion = funcionRepo.findById(funcionId)
                .orElseThrow(() -> new FuncionNoEncontradaException(funcionId));

        AsientoJpaEntity asientoEntity = funcion.getAsientos().stream()
                .filter(a -> a.getFila() == asiento.getFila() && a.getNumero() == asiento.getNumero())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Asiento no encontrado: " + asiento));

        asientoEntity.setEstado(EstadoAsiento.DISPONIBLE.name());
        funcionRepo.save(funcion);
    }
}
