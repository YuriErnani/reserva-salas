package br.com.alura.reserva_salas.controller;

import br.com.alura.reserva_salas.dto.salaDTO.SalaRequestDTO;
import br.com.alura.reserva_salas.dto.salaDTO.SalaResponseDTO;
import br.com.alura.reserva_salas.service.SalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/salas")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalaResponseDTO criar(@Valid @RequestBody SalaRequestDTO request) {
        return salaService.criar(request);
    }

    @GetMapping
    public List<SalaResponseDTO> listar() {
        return salaService.listarSalas();
    }

    @GetMapping("/{id}")
    public SalaResponseDTO buscarPorId(@PathVariable Long id) {
        return salaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public SalaResponseDTO atualizar(@Valid @PathVariable Long id, @RequestBody SalaRequestDTO request) {
        return salaService.atualizar(id, request);
    }

    @PatchMapping("/{id}/ativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long id) {
        salaService.ativar(id);
    }

    @PatchMapping("/{id}/desativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativar(@PathVariable Long id) {
        salaService.desativar(id);
    }

}
