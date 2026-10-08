package br.com.alura.reserva_salas.dto.salaDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record SalaRequestDTO(
        @NotBlank(message = "Nome da sala é obrigatório.")
        String nome,

        @Positive(message = "Capacidade da sala deve ser positiva.")
        int capacidade
) {
}
