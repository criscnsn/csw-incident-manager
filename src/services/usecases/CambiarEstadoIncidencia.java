package services.usecases;

import models.EstadoIncidencia;
import models.Incidencia;
import repository.IIncidenciaRepository;
import services.usecases.exceptions.IncidenciaNoEncontradaException;
import services.usecases.exceptions.TransicionEstadoInvalidaException;

public class CambiarEstadoIncidencia {
    private final IIncidenciaRepository<Incidencia> incidenciaRepository;

    public CambiarEstadoIncidencia(final IIncidenciaRepository<Incidencia> incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    public Incidencia ejecutar(final Incidencia incidencia, final EstadoIncidencia nuevoEstado) {
        if (incidencia == null) {
            throw new IncidenciaNoEncontradaException("La incidencia no puede ser nula.");
        }

        final EstadoIncidencia estadoActual = incidencia.getEstadoActual();

        // Máquina de estados: solo se permite PENDIENTE -> EN_PROCESO -> RESUELTA
        final boolean transicionValida = switch (estadoActual) {
            case PENDIENTE -> nuevoEstado == EstadoIncidencia.EN_PROCESO;
            case EN_PROCESO -> nuevoEstado == EstadoIncidencia.RESUELTA;
            case RESUELTA -> false;
        };

        if (!transicionValida) {
            throw new TransicionEstadoInvalidaException(estadoActual, nuevoEstado);
        }

        incidencia.setEstadoActual(nuevoEstado);
        return this.incidenciaRepository.save(incidencia);
    }
}
