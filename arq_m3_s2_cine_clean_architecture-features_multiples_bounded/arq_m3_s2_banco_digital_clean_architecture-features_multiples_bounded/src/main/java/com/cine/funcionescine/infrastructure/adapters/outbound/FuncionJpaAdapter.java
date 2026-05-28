package com.cine.funcionescine.infrastructure.adapters.outbound;

import com.cine.funcionescine.domain.model.Asiento;
import com.cine.funcionescine.domain.model.Funcion;
import com.cine.funcionescine.domain.model.Sala;
import com.cine.funcionescine.domain.model.vo.*;
import com.cine.funcionescine.domain.ports.outbound.FuncionRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FuncionJpaAdapter implements FuncionRepositoryPort {

    private final FuncionSpringRepository funcionRepo;
    private final SalaSpringRepository salaRepo;

    public FuncionJpaAdapter(FuncionSpringRepository funcionRepo, SalaSpringRepository salaRepo) {
        this.funcionRepo = funcionRepo;
        this.salaRepo = salaRepo;
    }

    @Override
    public void guardar(Funcion funcion) {
        FuncionJpaEntity entity = toEntity(funcion);
        funcionRepo.save(entity);
    }

    @Override
    public Optional<Funcion> buscarPorId(String funcionId) {
        return funcionRepo.findById(funcionId).map(this::toDomain);
    }

    private FuncionJpaEntity toEntity(Funcion funcion) {
        SalaJpaEntity salaEntity = salaRepo.findById(funcion.getSala().getId())
                .orElseThrow();

        FuncionJpaEntity funcionEntity = FuncionJpaEntity.builder()
                .id(funcion.getId())
                .peliculaId(funcion.getPeliculaId())
                .sala(salaEntity)
                .horarioFecha(funcion.getHorario().getFecha())
                .horarioHora(funcion.getHorario().getHora())
                .monto(funcion.getPrecio().getMonto())
                .moneda(funcion.getPrecio().getMoneda())
                .tipoFuncion(funcion.getTipoFuncion().name())
                .build();

        List<AsientoJpaEntity> asientoEntities = funcion.getAsientos().stream()
                .map(a -> AsientoJpaEntity.builder()
                        .fila(a.getNumero().getFila())
                        .numero(a.getNumero().getNumero())
                        .estado(a.getEstado().name())
                        .funcion(funcionEntity)
                        .build())
                .toList();

        funcionEntity.setAsientos(asientoEntities);
        return funcionEntity;
    }

    private Funcion toDomain(FuncionJpaEntity entity) {
        Sala sala = new Sala(entity.getSala().getId(), entity.getSala().getNombre(), entity.getSala().getCapacidad());
        HorarioFuncion horario = new HorarioFuncion(entity.getHorarioFecha(), entity.getHorarioHora());
        Precio precio = new Precio(entity.getMonto(), entity.getMoneda());
        TipoFuncion tipo = TipoFuncion.valueOf(entity.getTipoFuncion());

        List<Asiento> asientos = entity.getAsientos().stream()
                .map(a -> new Asiento(
                        new NumeroAsiento(a.getFila(), a.getNumero()),
                        EstadoAsiento.valueOf(a.getEstado())
                ))
                .toList();

        return Funcion.reconstituir(entity.getId(), entity.getPeliculaId(), sala, horario, precio, tipo, asientos);
    }
}
