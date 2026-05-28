package com.cine.reservas.domain.model;

import com.cine.funcionescine.domain.model.vo.NumeroAsiento;
import com.cine.reservas.domain.events.AsientosConfirmados;
import com.cine.reservas.domain.events.PagoRealizado;
import com.cine.reservas.domain.events.ReservaAprobada;
import com.cine.reservas.domain.events.ReservaCancelada;
import com.cine.reservas.domain.events.ReservaIniciada;
import com.cine.reservas.domain.exceptions.CancelacionNoPermitidaException;
import com.cine.reservas.domain.exceptions.ReservaNoPermitidaException;
import com.cine.shared.domain.DomainEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Reserva - Invariantes del Agregado")
class ReservaTest {

    private static final String CLIENTE_ID = "CLI-1";
    private static final String FUNCION_ID = "FUN-1";

    private List<AsientoReservado> asientos(int cantidad) {
        List<AsientoReservado> lista = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            lista.add(new AsientoReservado(new NumeroAsiento('A', i)));
        }
        return lista;
    }

    private LocalDateTime funcionDentroDeUnaHora() {
        return LocalDateTime.now().plusHours(2);
    }

    // ─── Invariante 2: anticipación mínima de 30 minutos ─────────────────────

    @Test
    @DisplayName("Invariante 2: reservar con más de 30 min de anticipación es válido")
    void iniciar_anticipacionSuficiente_exitoso() {
        LocalDateTime inicio = LocalDateTime.now().plusMinutes(45);

        assertThatCode(() -> Reserva.iniciar("R-1", CLIENTE_ID, FUNCION_ID, inicio, asientos(2)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Invariante 2: reservar con menos de 30 min de anticipación lanza excepción")
    void iniciar_menosDe30MinAntes_lanzaReservaNoPermitidaException() {
        LocalDateTime inicioCasi = LocalDateTime.now().plusMinutes(20);

        assertThatThrownBy(() ->
                Reserva.iniciar("R-2", CLIENTE_ID, FUNCION_ID, inicioCasi, asientos(2)))
                .isInstanceOf(ReservaNoPermitidaException.class)
                .hasMessageContaining("30 minutos");
    }

    @Test
    @DisplayName("Invariante 2: reservar exactamente en el horario de inicio lanza excepción")
    void iniciar_horarioExacto_lanzaExcepcion() {
        LocalDateTime ahoraExacto = LocalDateTime.now();

        assertThatThrownBy(() ->
                Reserva.iniciar("R-3", CLIENTE_ID, FUNCION_ID, ahoraExacto, asientos(1)))
                .isInstanceOf(ReservaNoPermitidaException.class);
    }

    // ─── Invariante 3: entre 1 y 10 asientos ─────────────────────────────────

    @Test
    @DisplayName("Invariante 3: reservar con 1 asiento es válido")
    void iniciar_unAsiento_exitoso() {
        assertThatCode(() ->
                Reserva.iniciar("R-4", CLIENTE_ID, FUNCION_ID, funcionDentroDeUnaHora(), asientos(1)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Invariante 3: reservar con 10 asientos es válido")
    void iniciar_diezAsientos_exitoso() {
        assertThatCode(() ->
                Reserva.iniciar("R-5", CLIENTE_ID, FUNCION_ID, funcionDentroDeUnaHora(), asientos(10)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Invariante 3: reservar con 11 asientos lanza ReservaNoPermitidaException")
    void iniciar_oncePlusAsientos_lanzaReservaNoPermitidaException() {
        assertThatThrownBy(() ->
                Reserva.iniciar("R-6", CLIENTE_ID, FUNCION_ID, funcionDentroDeUnaHora(), asientos(11)))
                .isInstanceOf(ReservaNoPermitidaException.class)
                .hasMessageContaining("10");
    }

    @Test
    @DisplayName("Invariante 3: reservar con 0 asientos lanza ReservaNoPermitidaException")
    void iniciar_ceroAsientos_lanzaReservaNoPermitidaException() {
        assertThatThrownBy(() ->
                Reserva.iniciar("R-7", CLIENTE_ID, FUNCION_ID, funcionDentroDeUnaHora(), asientos(0)))
                .isInstanceOf(ReservaNoPermitidaException.class);
    }

    @Test
    @DisplayName("Invariante 3: reservar con lista null lanza ReservaNoPermitidaException")
    void iniciar_listaNull_lanzaReservaNoPermitidaException() {
        assertThatThrownBy(() ->
                Reserva.iniciar("R-8", CLIENTE_ID, FUNCION_ID, funcionDentroDeUnaHora(), null))
                .isInstanceOf(ReservaNoPermitidaException.class);
    }

    // ─── Flujo normal: iniciar → aprobar ─────────────────────────────────────

    @Test
    @DisplayName("Aprobar reserva la deja en estado CONFIRMADA y emite 3 eventos")
    void aprobar_reservaPendiente_estadoConfirmadaYEventos() {
        Reserva reserva = Reserva.iniciar("R-9", CLIENTE_ID, FUNCION_ID, funcionDentroDeUnaHora(), asientos(3));
        reserva.pullDomainEvents(); // limpiar ReservaIniciada

        reserva.aprobar();

        assertThat(reserva.getEstado()).isEqualTo(EstadoReserva.CONFIRMADA);

        List<DomainEvent> events = reserva.pullDomainEvents();
        assertThat(events).hasSize(3);
        assertThat(events).anyMatch(e -> e instanceof ReservaAprobada);
        assertThat(events).anyMatch(e -> e instanceof AsientosConfirmados);
        assertThat(events).anyMatch(e -> e instanceof PagoRealizado);
    }

    @Test
    @DisplayName("Iniciar reserva emite ReservaIniciada con cantidad correcta")
    void iniciar_emiteReservaIniciada() {
        Reserva reserva = Reserva.iniciar("R-10", CLIENTE_ID, FUNCION_ID, funcionDentroDeUnaHora(), asientos(5));

        List<DomainEvent> events = reserva.pullDomainEvents();
        assertThat(events).hasSize(1);
        assertThat(events.get(0)).isInstanceOf(ReservaIniciada.class);
        ReservaIniciada evento = (ReservaIniciada) events.get(0);
        assertThat(evento.getCantidadAsientos()).isEqualTo(5);
    }

    // ─── Invariante 4: cancelación hasta 1 hora antes ────────────────────────

    @Test
    @DisplayName("Invariante 4: cancelar con más de 1 hora de anticipación es válido")
    void cancelar_masDeUnaHoraAntes_exitoso() {
        LocalDateTime inicio = LocalDateTime.now().plusHours(3);
        Reserva reserva = Reserva.iniciar("R-11", CLIENTE_ID, FUNCION_ID, inicio, asientos(2));
        reserva.pullDomainEvents();

        assertThatCode(() -> reserva.cancelar(inicio)).doesNotThrowAnyException();
        assertThat(reserva.getEstado()).isEqualTo(EstadoReserva.CANCELADA);
    }

    @Test
    @DisplayName("Invariante 4: cancelar con menos de 1 hora lanza CancelacionNoPermitidaException")
    void cancelar_menosDeUnaHoraAntes_lanzaCancelacionNoPermitidaException() {
        LocalDateTime inicio = LocalDateTime.now().plusMinutes(45);
        Reserva reserva = Reserva.iniciar("R-12", CLIENTE_ID, FUNCION_ID, inicio, asientos(2));

        assertThatThrownBy(() -> reserva.cancelar(inicio))
                .isInstanceOf(CancelacionNoPermitidaException.class)
                .hasMessageContaining("1 hora");
    }

    @Test
    @DisplayName("Invariante 7: cancelar emite ReservaCancelada con asientos a liberar")
    void cancelar_emiteReservaCanceladaConAsientos() {
        LocalDateTime inicio = LocalDateTime.now().plusHours(3);
        Reserva reserva = Reserva.iniciar("R-13", CLIENTE_ID, FUNCION_ID, inicio, asientos(3));
        reserva.pullDomainEvents();

        reserva.cancelar(inicio);

        List<DomainEvent> events = reserva.pullDomainEvents();
        assertThat(events).hasSize(1);
        assertThat(events.get(0)).isInstanceOf(ReservaCancelada.class);
        ReservaCancelada evento = (ReservaCancelada) events.get(0);
        assertThat(evento.getAsientosLiberados()).hasSize(3);
    }
}
