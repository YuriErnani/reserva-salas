package br.com.alura.reserva_salas.dto.reservaDTO;

import br.com.alura.reserva_salas.model.reserva.Reserva;
import br.com.alura.reserva_salas.model.reserva.StatusReserva;

import java.time.LocalDateTime;

public record ReservaResponseDTO(Long id,
                                 Long salaId,
                                 Long usuarioId,
                                 LocalDateTime inicio,
                                 LocalDateTime fim,
                                 StatusReserva status) {

    public ReservaResponseDTO(Reserva reserva) {
        this(reserva.getId(), reserva.getSala().getId(), reserva.getUsuario().getId(), reserva.getInicio(), reserva.getFim(), reserva.getStatus());
    }

}
