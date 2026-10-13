package br.com.alura.reserva_salas.dto.securityDTO;

import jakarta.validation.constraints.NotBlank;

public record DadosLoginDTO(
        @NotBlank(message = "Login do usuário obrigatório")
        String login,

        @NotBlank(message = "Senha do usuário obrigatória")
        String password
) {
}
