package com.cine.reservas.domain.ports.outbound;

import com.cine.reservas.domain.model.Reserva;

import java.util.Optional;

public interface ReservaRepositoryPort {

    void guardar(Reserva reserva);

    Optional<Reserva> buscarPorId(String reservaId);
}
