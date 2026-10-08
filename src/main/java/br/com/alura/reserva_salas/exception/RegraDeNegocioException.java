package br.com.alura.reserva_salas.exception;

public class RegraDeNegocioException extends RuntimeException {

    private final String field;

    public RegraDeNegocioException(String message) {
        this(message, null);
    }

    public RegraDeNegocioException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
