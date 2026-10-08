package br.com.alura.reserva_salas.repository;

import br.com.alura.reserva_salas.model.sala.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {
}
