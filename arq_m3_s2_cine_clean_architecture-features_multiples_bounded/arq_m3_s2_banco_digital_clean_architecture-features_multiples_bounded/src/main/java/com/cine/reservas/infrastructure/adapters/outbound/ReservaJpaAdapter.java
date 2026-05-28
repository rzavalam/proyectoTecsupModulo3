package com.cine.reservas.infrastructure.adapters.outbound;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;
import com.cine.reservas.domain.model.AsientoReservado;
import com.cine.reservas.domain.model.EstadoReserva;
import com.cine.reservas.domain.model.Reserva;
import com.cine.reservas.domain.ports.outbound.ReservaRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReservaJpaAdapter implements ReservaRepositoryPort {

    private final ReservaSpringRepository repository;

    public ReservaJpaAdapter(ReservaSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public void guardar(Reserva reserva) {
        ReservaJpaEntity entity = toEntity(reserva);
        repository.save(entity);
    }

    @Override
    public Optional<Reserva> buscarPorId(String reservaId) {
        return repository.findById(reservaId).map(this::toDomain);
    }

    private ReservaJpaEntity toEntity(Reserva reserva) {
        ReservaJpaEntity entity = ReservaJpaEntity.builder()
                .id(reserva.getId())
                .clienteId(reserva.getClienteId())
                .funcionId(reserva.getFuncionId())
                .fechaReserva(reserva.getFechaReserva())
                .estado(reserva.getEstado().name())
                .build();

        List<AsientoReservadoJpaEntity> asientos = reserva.getAsientos().stream()
                .map(a -> AsientoReservadoJpaEntity.builder()
                        .fila(a.getNumero().getFila())
                        .numero(a.getNumero().getNumero())
                        .reserva(entity)
                        .build())
                .toList();
        entity.setAsientos(asientos);
        return entity;
    }

    private Reserva toDomain(ReservaJpaEntity entity) {
        List<AsientoReservado> asientos = entity.getAsientos().stream()
                .map(a -> new AsientoReservado(new NumeroAsiento(a.getFila(), a.getNumero())))
                .toList();

        return Reserva.reconstituir(
                entity.getId(),
                entity.getClienteId(),
                entity.getFuncionId(),
                entity.getFechaReserva(),
                EstadoReserva.valueOf(entity.getEstado()),
                asientos
        );
    }
}
