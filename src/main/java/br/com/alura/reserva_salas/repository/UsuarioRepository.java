package br.com.alura.reserva_salas.repository;

import br.com.alura.reserva_salas.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
