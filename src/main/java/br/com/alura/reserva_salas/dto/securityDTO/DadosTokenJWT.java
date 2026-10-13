package br.com.alura.reserva_salas.dto.securityDTO;

import jakarta.validation.constraints.NotBlank;

public record DadosTokenJWT(
        @NotBlank(message = "Token obrigatório")
        String token
) {
}
