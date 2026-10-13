package br.com.alura.reserva_salas.repository;

import br.com.alura.reserva_salas.model.security.UsuarioAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityRepository extends JpaRepository<UsuarioAuth, Long> {

    UserDetails findByLogin(String login);

}
