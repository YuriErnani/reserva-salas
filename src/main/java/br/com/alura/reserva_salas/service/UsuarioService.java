package br.com.alura.reserva_salas.service;

import br.com.alura.reserva_salas.dto.usuarioDTO.UsuarioRequestDTO;
import br.com.alura.reserva_salas.dto.usuarioDTO.UsuarioResponseDTO;
import br.com.alura.reserva_salas.infra.exception.RecursoNaoEncontradoException;
import br.com.alura.reserva_salas.model.usuario.Usuario;
import br.com.alura.reserva_salas.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO request) {
        var usuario = new Usuario(request.nome(), request.email());

        usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuario);
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        return new UsuarioResponseDTO(usuario);
    }

    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO request) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        usuario.atualizar(request.nome(), request.email());

        return new UsuarioResponseDTO(usuario);
    }

    @Transactional
    public void excluir(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        usuarioRepository.delete(usuario);
    }

}
