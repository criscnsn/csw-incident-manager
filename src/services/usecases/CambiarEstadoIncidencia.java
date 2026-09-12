package services.usecases;

import models.EstadoIncidencia;
import models.Incidencia;
import models.PrioridadIncidencia;
import services.usecases.exceptions.IncidenciaNoEncontradaException;
import services.usecases.exceptions.TransicionEstadoInvalidaException;
import storage.IIncidenciaStorage;

public class CambiarEstadoIncidencia {
    private final IIncidenciaStorage incidenciaStorage;

    public CambiarEstadoIncidencia(final IIncidenciaStorage incidenciaStorage) {
        this.incidenciaStorage = incidenciaStorage;
    }

    public Incidencia ejecutar(final Incidencia incidencia, final EstadoIncidencia nuevoEstado) {
        if (incidencia == null) {
            throw new IncidenciaNoEncontradaException("La incidencia no puede ser nula.");
        }

        final EstadoIncidencia estadoActual = incidencia.getEstadoActual();

        // Máquina de estados: solo se permite PENDIENTE -> EN_PROCESO -> RESUELTA

        boolean transicionValida = isTransicionValida(
                incidencia,
                nuevoEstado,
                estadoActual
        );


        if (!transicionValida) {
            throw new TransicionEstadoInvalidaException(estadoActual, nuevoEstado);
        }

        incidencia.setEstadoActual(nuevoEstado);
        return this.incidenciaStorage.save(incidencia);
    }

    private static boolean isTransicionValida(
            Incidencia incidencia,
            EstadoIncidencia nuevoEstado,
            EstadoIncidencia estadoActual
    ) {
        final PrioridadIncidencia prioridadActual = incidencia.getPrioridad();


        boolean transicionValida;
        if (prioridadActual == PrioridadIncidencia.ALTA) {
            transicionValida = switch (estadoActual) {
                case PENDIENTE -> nuevoEstado == EstadoIncidencia.EN_PROCESO;
                case EN_PROCESO -> nuevoEstado == EstadoIncidencia.RESUELTA;
                case RESUELTA -> false;
            };
        } else {
            transicionValida = switch (estadoActual) {
                case PENDIENTE -> nuevoEstado != EstadoIncidencia.PENDIENTE;
                case EN_PROCESO -> nuevoEstado == EstadoIncidencia.RESUELTA;
                case RESUELTA -> false;
            };
        }
        return transicionValida;
    }
}
