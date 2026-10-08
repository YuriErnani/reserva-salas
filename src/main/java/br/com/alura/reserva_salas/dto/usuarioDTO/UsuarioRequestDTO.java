package br.com.alura.reserva_salas.dto.usuarioDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank(message = "Nome do usuário obrigatório")
        String nome,

        @NotBlank(message = "Email do usuário obrigatório")
        @Email(message = "Email inválido.")
        String email

) {
}
