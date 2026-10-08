package br.com.alura.reserva_salas.controller;

import br.com.alura.reserva_salas.dto.reservaDTO.ReservaRequestDTO;
import br.com.alura.reserva_salas.dto.reservaDTO.ReservaResponseDTO;
import br.com.alura.reserva_salas.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaResponseDTO criar(@Valid @RequestBody ReservaRequestDTO request) {
        return reservaService.criarReserva(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
    }

}
