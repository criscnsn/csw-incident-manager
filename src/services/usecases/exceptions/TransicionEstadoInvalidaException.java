package services.usecases.exceptions;

import models.EstadoIncidencia;

public class TransicionEstadoInvalidaException extends RuntimeException {
    public TransicionEstadoInvalidaException(final EstadoIncidencia estadoActual, final EstadoIncidencia nuevoEstado) {
        super("Transición de estado no permitida. No se puede pasar de " + estadoActual + " a " + nuevoEstado + ".");
    }
}