package br.com.alura.reserva_salas.model.reserva;

import br.com.alura.reserva_salas.exception.RegraDeNegocioException;
import br.com.alura.reserva_salas.model.Usuario;
import br.com.alura.reserva_salas.model.sala.Sala;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ReservaTest {

    @Test
    void identificadorDeConflitoEntreReservasAtivas() {

        var inicio = LocalDateTime.of(2026, 10, 8, 10, 0);
        var fim = LocalDateTime.of(2026, 10, 8, 11, 0);

        var sala = mock(Sala.class);
        var usuario = mock(Usuario.class);

        var reserva = new Reserva(sala, usuario, inicio, fim);

        var novoInicio = LocalDateTime.of(2026, 10, 8, 10, 30);
        var novoFim = LocalDateTime.of(2026, 10, 8, 11, 30);

        var conflito = reserva.conflita(novoInicio, novoFim);

        assertTrue(conflito);

    }

    @Test
    void identificadorDeConflitoQuandoNovaReservaComecaNoFimDaAnterior() {

        var inicio = LocalDateTime.of(2026, 10, 8, 10, 0);
        var fim = LocalDateTime.of(2026, 10, 8, 11, 0);

        var sala = mock(Sala.class);
        var usuario = mock(Usuario.class);

        var reserva = new Reserva(sala, usuario, inicio, fim);

        var novoInicio = LocalDateTime.of(2026, 10, 8, 11, 0);
        var novoFim = LocalDateTime.of(2026, 10, 8, 12, 0);

        var conflito = reserva.conflita(novoInicio, novoFim);

        assertFalse(conflito);
    }

    @Test
    void identificadorDeConflitoComReservasCanceladas() {

        var inicio = LocalDateTime.of(2026, 10, 8, 10, 0);
        var fim = LocalDateTime.of(2026, 10, 8, 11, 0);

        var sala = mock(Sala.class);
        var usuario = mock(Usuario.class);

        var reserva = new Reserva(sala, usuario, inicio, fim);

        reserva.cancelar();

        var novoInicio = LocalDateTime.of(2026, 10, 8, 10, 30);
        var novoFim = LocalDateTime.of(2026, 10, 8, 11, 30);

        var conflito = reserva.conflita(novoInicio, novoFim);

        assertFalse(conflito);

    }

    @Test
    void identificadorDeConflitoQuandoNovaReservaTerminaNoInicioDaAnterior() {

        LocalDateTime inicio = LocalDateTime.of(2026, 10, 8, 10, 0);
        LocalDateTime fim = LocalDateTime.of(2026, 10, 8, 11, 0);

        var sala = mock(Sala.class);
        var usuario = mock(Usuario.class);

        var reserva = new Reserva(sala, usuario, inicio, fim);

        var novoInicio = LocalDateTime.of(2026, 10, 8, 9, 0);
        var novoFim = LocalDateTime.of(2026, 10, 8, 10, 0);

        var conflito = reserva.conflita(novoInicio, novoFim);

        assertFalse(conflito);

    }

    @Test
    void deveRejeitarReservaQuandoFimNaoForPosteriorAoInicio() {

        LocalDateTime inicio = LocalDateTime.of(2026, 10, 8, 11, 0);
        LocalDateTime fim = LocalDateTime.of(2026, 10, 8, 10, 0);

        var sala = mock(Sala.class);
        var usuario = mock(Usuario.class);

        assertThrows(RegraDeNegocioException.class, () -> new Reserva(sala, usuario, inicio, fim));

    }

}
