package br.com.alura.reserva_salas.dto.reservaDTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ReservaRequestDTO(
        @NotNull(message = "Sala é obrigatória.")
        Long salaId,

        @NotNull(message = "Usuário é obrigatório.")
        Long usuarioId,

        @NotNull(message = "Início da reserva é obrigatório.")
        @Future(message = "O início da reserva deve estar no futuro.")
        LocalDateTime inicio,

        @NotNull(message = "Fim da reserva é obrigatório.")
        @Future(message = "O fim da reserva deve estar no futuro.")
        LocalDateTime fim
) {
}
