package br.com.alura.reserva_salas.infra.exception;

import br.com.alura.reserva_salas.dto.erroDTO.CampoErroDTO;
import br.com.alura.reserva_salas.dto.erroDTO.ErroResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroResponseDTO> tratarRegraDeNegocio(RegraDeNegocioException exception) {
        List<CampoErroDTO> erros = List.of();

        if (exception.getField() != null) erros = List.of(new CampoErroDTO(exception.getField(), exception.getMessage()));

        var erro = new ErroResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                erros,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponseDTO> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException exception) {
        var erroCampo = new CampoErroDTO(null, exception.getMessage());

        var erro = new ErroResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                List.<CampoErroDTO>of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDTO> tratarErroValidacao(MethodArgumentNotValidException exception) {
        var erros = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> new CampoErroDTO(
                        erro.getField(),
                        erro.getDefaultMessage()
                ))
                .toList();

        var erro = new ErroResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Dados inválidos.",
                erros,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);

    }

}
