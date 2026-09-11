package services.usecases.exceptions;

public class DatosIncidenciaInvalidosException extends RuntimeException {

    public DatosIncidenciaInvalidosException(final String message) {
        super(message);
    }
    
}