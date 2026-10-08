package br.com.alura.reserva_salas.repository;

import br.com.alura.reserva_salas.model.reserva.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findBySalaId(Long salaId);

    Page<Reserva> findBySalaIdAndInicioLessThanAndFimGreaterThan(Long salaId, LocalDateTime fim, LocalDateTime inicio, Pageable pageable
    );

}
