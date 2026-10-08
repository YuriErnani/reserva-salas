package br.com.alura.reserva_salas.dto.erroDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ErroResponseDTO(
        int status,
        String message,
        List<CampoErroDTO> errors,
        LocalDateTime timestamp
) {
}
