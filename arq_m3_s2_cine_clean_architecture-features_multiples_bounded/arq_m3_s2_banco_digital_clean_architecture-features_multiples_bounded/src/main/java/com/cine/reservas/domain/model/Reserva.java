package com.cine.reservas.domain.model;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;
import com.cine.reservas.domain.events.*;
import com.cine.reservas.domain.exceptions.CancelacionNoPermitidaException;
import com.cine.reservas.domain.exceptions.ReservaNoPermitidaException;
import com.cine.shared.domain.DomainEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Agregado raíz del Bounded Context GestiónDeReservas.
 *
 * Invariantes:
 * - Inv 2: reserva con al menos 30 min de anticipación al inicio de la función.
 * - Inv 3: entre 1 y 10 asientos por reserva.
 * - Inv 4: cancelación hasta 1 hora antes de la función.
 */
public class Reserva {

    private final String id;
    private final String clienteId;
    private final String funcionId;
    private final LocalDateTime fechaReserva;
    private EstadoReserva estado;
    private final List<AsientoReservado> asientos;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private Reserva(String id, String clienteId, String funcionId,
                    LocalDateTime fechaReserva, List<AsientoReservado> asientos) {
        this.id = id;
        this.clienteId = clienteId;
        this.funcionId = funcionId;
        this.fechaReserva = fechaReserva;
        this.asientos = new ArrayList<>(asientos);
        this.estado = EstadoReserva.PENDIENTE;
    }

    /**
     * Inicia una nueva reserva aplicando las invariantes de negocio 2 y 3.
     *
     * @param inicioFuncion fecha/hora de inicio de la función
     */
    public static Reserva iniciar(String id, String clienteId, String funcionId,
                                   LocalDateTime inicioFuncion, List<AsientoReservado> asientos) {
        // Invariante 3: entre 1 y 10 asientos
        if (asientos == null || asientos.isEmpty() || asientos.size() > 10) {
            throw new ReservaNoPermitidaException(
                    "La reserva debe incluir entre 1 y 10 asientos, solicitados: "
                    + (asientos == null ? 0 : asientos.size()));
        }

        // Invariante 2: al menos 30 minutos de anticipación
        LocalDateTime ahora = LocalDateTime.now();
        if (!inicioFuncion.minusMinutes(30).isAfter(ahora)) {
            throw new ReservaNoPermitidaException(
                    "La reserva debe hacerse con al menos 30 minutos de anticipación al inicio de la función");
        }

        Reserva reserva = new Reserva(id, clienteId, funcionId, ahora, asientos);
        reserva.domainEvents.add(new ReservaIniciada(id, clienteId, funcionId, asientos.size()));
        return reserva;
    }

    /** Reconstituye el agregado desde persistencia sin emitir eventos. */
    public static Reserva reconstituir(String id, String clienteId, String funcionId,
                                        LocalDateTime fechaReserva, EstadoReserva estado,
                                        List<AsientoReservado> asientos) {
        Reserva reserva = new Reserva(id, clienteId, funcionId, fechaReserva, asientos);
        reserva.estado = estado;
        return reserva;
    }

    /** Confirma la reserva, emitiendo los eventos de aprobación y pago. */
    public void aprobar() {
        this.estado = EstadoReserva.CONFIRMADA;
        List<NumeroAsiento> numeros = asientos.stream().map(AsientoReservado::getNumero).toList();
        domainEvents.add(new ReservaAprobada(id, clienteId, funcionId));
        domainEvents.add(new AsientosConfirmados(id, funcionId, numeros));
        domainEvents.add(new PagoRealizado(id, clienteId, asientos.size()));
    }

    /**
     * Cancela la reserva liberando asientos e iniciando la devolución.
     * Invariante 4: solo se puede cancelar hasta 1 hora antes de la función.
     *
     * @param inicioFuncion fecha/hora de inicio de la función
     */
    public void cancelar(LocalDateTime inicioFuncion) {
        LocalDateTime ahora = LocalDateTime.now();
        if (!inicioFuncion.minusHours(1).isAfter(ahora)) {
            throw new CancelacionNoPermitidaException(
                    "La reserva solo puede cancelarse hasta 1 hora antes del inicio de la función");
        }
        this.estado = EstadoReserva.CANCELADA;
        List<NumeroAsiento> numeros = asientos.stream().map(AsientoReservado::getNumero).toList();
        domainEvents.add(new ReservaCancelada(id, clienteId, funcionId, numeros));
    }

    /** Extrae y limpia los eventos de dominio acumulados (patrón pull). */
    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }

    public String getId() { return id; }
    public String getClienteId() { return clienteId; }
    public String getFuncionId() { return funcionId; }
    public LocalDateTime getFechaReserva() { return fechaReserva; }
    public EstadoReserva getEstado() { return estado; }
    public List<AsientoReservado> getAsientos() { return Collections.unmodifiableList(asientos); }
    public int getCantidadAsientos() { return asientos.size(); }
}
