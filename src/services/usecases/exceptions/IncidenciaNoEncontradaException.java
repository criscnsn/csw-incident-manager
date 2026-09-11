package services.usecases.exceptions;

public class IncidenciaNoEncontradaException extends RuntimeException {
    public IncidenciaNoEncontradaException(final String message) {
        super(message);
    }
}
