package com.cine.funcionescine.domain.model;

import com.cine.funcionescine.domain.events.FuncionCreada;
import com.cine.funcionescine.domain.exceptions.AsientoOcupadoException;
import com.cine.funcionescine.domain.model.vo.*;
import com.cine.shared.domain.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Funcion - Invariantes del Agregado")
class FuncionTest {

    private Sala sala;
    private HorarioFuncion horario;
    private Precio precio;

    @BeforeEach
    void setUp() {
        sala = new Sala("SALA-1", "Sala Premium", 60);
        horario = new HorarioFuncion(LocalDate.now().plusDays(1), LocalTime.of(20, 0));
        precio = new Precio(new BigDecimal("25.00"), "PEN");
    }

    @Test
    @DisplayName("Crear función genera asientos según capacidad de sala y emite FuncionCreada")
    void crear_exitoso_generaAsientosYEmiteEvento() {
        Funcion funcion = Funcion.crear("FUN-1", "PEL-1", sala, horario, precio, TipoFuncion.ESTANDAR);

        assertThat(funcion.getId()).isEqualTo("FUN-1");
        assertThat(funcion.getAsientos()).hasSize(60);
        assertThat(funcion.getAsientos()).allMatch(Asiento::estaDisponible);

        List<DomainEvent> events = funcion.pullDomainEvents();
        assertThat(events).hasSize(1);
        assertThat(events.get(0)).isInstanceOf(FuncionCreada.class);
        assertThat(((FuncionCreada) events.get(0)).getFuncionId()).isEqualTo("FUN-1");
    }

    @Test
    @DisplayName("Crear función con horario en el pasado lanza excepción")
    void crear_horarioPasado_lanzaExcepcion() {
        HorarioFuncion horarioPasado = new HorarioFuncion(LocalDate.now().minusDays(1), LocalTime.of(10, 0));

        assertThatThrownBy(() ->
                Funcion.crear("FUN-2", "PEL-1", sala, horarioPasado, precio, TipoFuncion.ESTANDAR))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("futuro");
    }

    @Test
    @DisplayName("Invariante 5: sala de capacidad 0 o negativa es inválida")
    void sala_capacidadInvalida_lanzaExcepcion() {
        assertThatThrownBy(() -> new Sala("SALA-X", "Inválida", 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContainingAll("capacidad");

        assertThatThrownBy(() -> new Sala("SALA-X", "Inválida", -5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Invariante 1: un asiento disponible puede reservarse")
    void reservarAsiento_disponible_exitoso() {
        Funcion funcion = Funcion.crear("FUN-3", "PEL-1", sala, horario, precio, TipoFuncion.VIP);
        NumeroAsiento asiento = new NumeroAsiento('A', 1);

        assertThatCode(() -> funcion.reservarAsiento(asiento)).doesNotThrowAnyException();

        Asiento a = funcion.getAsientos().stream()
                .filter(x -> x.getNumero().equals(asiento))
                .findFirst().orElseThrow();
        assertThat(a.estaDisponible()).isFalse();
    }

    @Test
    @DisplayName("Invariante 1: reservar el mismo asiento dos veces lanza AsientoOcupadoException")
    void reservarAsiento_yaReservado_lanzaAsientoOcupadoException() {
        Funcion funcion = Funcion.crear("FUN-4", "PEL-1", sala, horario, precio, TipoFuncion.TRES_D);
        NumeroAsiento asiento = new NumeroAsiento('B', 5);

        funcion.reservarAsiento(asiento);

        assertThatThrownBy(() -> funcion.reservarAsiento(asiento))
                .isInstanceOf(AsientoOcupadoException.class)
                .hasMessageContaining("B5");
    }

    @Test
    @DisplayName("Liberar un asiento lo vuelve disponible para nueva reserva")
    void liberarAsiento_reservadoPrevia_vuelveDisponible() {
        Funcion funcion = Funcion.crear("FUN-5", "PEL-1", sala, horario, precio, TipoFuncion.ESTANDAR);
        NumeroAsiento asiento = new NumeroAsiento('C', 3);

        funcion.reservarAsiento(asiento);
        funcion.liberarAsiento(asiento);

        assertThatCode(() -> funcion.reservarAsiento(asiento)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("NumeroAsiento invalido (fila fuera de A-Z) lanza excepción")
    void numeroAsiento_filaInvalida_lanzaExcepcion() {
        assertThatThrownBy(() -> new NumeroAsiento('1', 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("fila");
    }

    @Test
    @DisplayName("NumeroAsiento invalido (número fuera de 1-20) lanza excepción")
    void numeroAsiento_numeroInvalido_lanzaExcepcion() {
        assertThatThrownBy(() -> new NumeroAsiento('A', 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("número");

        assertThatThrownBy(() -> new NumeroAsiento('A', 21))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Precio con monto negativo o cero es inválido")
    void precio_montoInvalido_lanzaExcepcion() {
        assertThatThrownBy(() -> new Precio(BigDecimal.ZERO, "PEN"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("positivo");

        assertThatThrownBy(() -> new Precio(new BigDecimal("-10"), "PEN"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("pullDomainEvents limpia la lista de eventos tras extraerlos")
    void pullDomainEvents_limpiaEventosTrasExtraer() {
        Funcion funcion = Funcion.crear("FUN-6", "PEL-1", sala, horario, precio, TipoFuncion.ESTANDAR);

        List<DomainEvent> primera = funcion.pullDomainEvents();
        List<DomainEvent> segunda = funcion.pullDomainEvents();

        assertThat(primera).hasSize(1);
        assertThat(segunda).isEmpty();
    }
}
