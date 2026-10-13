package br.com.alura.reserva_salas.dto.usuarioDTO;

import br.com.alura.reserva_salas.model.usuario.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email
) {

    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

}
