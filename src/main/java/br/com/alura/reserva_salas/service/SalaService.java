package br.com.alura.reserva_salas.service;

import br.com.alura.reserva_salas.dto.salaDTO.SalaRequestDTO;
import br.com.alura.reserva_salas.dto.salaDTO.SalaResponseDTO;
import br.com.alura.reserva_salas.exception.RecursoNaoEncontradoException;
import br.com.alura.reserva_salas.model.sala.Sala;
import br.com.alura.reserva_salas.repository.SalaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;

    @Transactional
    public SalaResponseDTO criar(SalaRequestDTO request) {
        var sala = new Sala(request.nome(), request.capacidade());
        return new SalaResponseDTO(salaRepository.save(sala));
    }

    public List<SalaResponseDTO> listarSalas() {
        return salaRepository.findAll().stream()
                .map(SalaResponseDTO::new)
                .toList();
    }

    private Sala buscarSala(Long id) {
        return salaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Sala não encontrada."));
    }

    public SalaResponseDTO buscarPorId(Long id) {
        return new SalaResponseDTO(buscarSala(id));
    }

    @Transactional
    public SalaResponseDTO atualizar(Long id, SalaRequestDTO request) {
        var sala = buscarSala(id);
        sala.atualizar(request.nome(), request.capacidade());

        return new SalaResponseDTO(salaRepository.save(sala));
    }

    @Transactional
    public void ativar(Long id) {
        var sala = buscarSala(id);
        sala.ativar();

        salaRepository.save(sala);
    }

    @Transactional
    public void desativar(Long id) {
        var sala = buscarSala(id);
        sala.desativar();

        salaRepository.save(sala);
    }

}
