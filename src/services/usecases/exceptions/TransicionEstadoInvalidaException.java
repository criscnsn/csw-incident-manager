package services.usecases.exceptions;

import models.EstadoIncidencia;

public class TransicionEstadoInvalidaException extends RuntimeException {
    public TransicionEstadoInvalidaException(EstadoIncidencia estadoActual, EstadoIncidencia nuevoEstado) {
        super("Error x_x : Transicion de estado no permitida. No se puede pasar de " + estadoActual + " a " + nuevoEstado);
    }
}