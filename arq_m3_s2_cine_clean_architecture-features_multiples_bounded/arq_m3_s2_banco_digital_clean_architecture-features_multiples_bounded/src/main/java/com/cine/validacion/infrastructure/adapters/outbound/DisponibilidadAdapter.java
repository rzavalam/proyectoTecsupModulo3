package com.cine.validacion.infrastructure.adapters.outbound;

import com.cine.funcionescine.domain.model.vo.EstadoAsiento;
import com.cine.funcionescine.domain.model.vo.NumeroAsiento;
import com.cine.funcionescine.infrastructure.adapters.outbound.AsientoJpaEntity;
import com.cine.funcionescine.infrastructure.adapters.outbound.FuncionSpringRepository;
import com.cine.validacion.domain.ports.outbound.DisponibilidadQueryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Adaptador de salida del BC ValidaciónDeReglas.
 * Consulta directamente las entidades JPA del BC GestiónDeFunciones
 * (acceso en monolito al nivel de infraestructura, sin cruzar puertos de dominio).
 */
@Component
public class DisponibilidadAdapter implements DisponibilidadQueryPort {

    private final FuncionSpringRepository funcionRepo;

    public DisponibilidadAdapter(FuncionSpringRepository funcionRepo) {
        this.funcionRepo = funcionRepo;
    }

    @Override
    public boolean todosDisponibles(String funcionId, List<NumeroAsiento> asientos) {
        return funcionRepo.findById(funcionId)
                .map(f -> {
                    Set<String> disponibles = f.getAsientos().stream()
                            .filter(a -> EstadoAsiento.DISPONIBLE.name().equals(a.getEstado()))
                            .map(a -> String.valueOf(a.getFila()) + a.getNumero())
                            .collect(Collectors.toSet());

                    return asientos.stream()
                            .map(NumeroAsiento::getCodigo)
                            .allMatch(disponibles::contains);
                })
                .orElse(false);
    }

    @Override
    public long contarDisponibles(String funcionId) {
        return funcionRepo.findById(funcionId)
                .map(f -> f.getAsientos().stream()
                        .filter(a -> EstadoAsiento.DISPONIBLE.name().equals(a.getEstado()))
                        .count())
                .orElse(0L);
    }
}
