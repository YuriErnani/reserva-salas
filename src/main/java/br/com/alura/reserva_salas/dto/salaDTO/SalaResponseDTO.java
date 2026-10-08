package br.com.alura.reserva_salas.dto.salaDTO;

import br.com.alura.reserva_salas.model.sala.Sala;
import br.com.alura.reserva_salas.model.sala.StatusSala;

public record SalaResponseDTO(Long id,
                              String nome,
                              int capacidade,
                              StatusSala status) {

    public SalaResponseDTO(Sala sala) {
        this(sala.getId(), sala.getNome(), sala.getCapacidade(), sala.getStatus());
    }

}
