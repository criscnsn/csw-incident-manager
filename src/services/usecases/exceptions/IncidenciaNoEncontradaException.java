package services.usecases.exceptions;

public class IncidenciaNoEncontradaException extends RuntimeException {
    public IncidenciaNoEncontradaException(String message) {
        super(message);
    }
}
