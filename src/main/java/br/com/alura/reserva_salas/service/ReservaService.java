package br.com.alura.reserva_salas.service;

import br.com.alura.reserva_salas.dto.reservaDTO.ReservaRequestDTO;
import br.com.alura.reserva_salas.dto.reservaDTO.ReservaResponseDTO;
import br.com.alura.reserva_salas.infra.exception.RecursoNaoEncontradoException;
import br.com.alura.reserva_salas.infra.exception.RegraDeNegocioException;
import br.com.alura.reserva_salas.model.reserva.Reserva;
import br.com.alura.reserva_salas.repository.ReservaRepository;
import br.com.alura.reserva_salas.repository.SalaRepository;
import br.com.alura.reserva_salas.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public ReservaResponseDTO criarReserva(ReservaRequestDTO request) {
        var sala = salaRepository.findById(request.salaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Sala não encontrada."));

        var usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        var reservasDaSala = reservaRepository.findBySalaId(sala.getId());

        boolean existeConflito = reservasDaSala.stream()
                .anyMatch(reserva ->reserva.conflita(request.inicio(), request.fim()));

        if (existeConflito) throw new RegraDeNegocioException("Já existe uma reserva para a sala nesse horário.");

        var reserva = new Reserva(sala, usuario, request.inicio(), request.fim());

        reservaRepository.save(reserva);

        return new ReservaResponseDTO(reserva);
    }

    @Transactional
    public void cancelarReserva(Long id) {
        var reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Reserva não encontrada."));

        reserva.cancelar();

        reservaRepository.save(reserva);
    }

}
