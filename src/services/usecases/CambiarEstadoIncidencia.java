package services.usecases;

import models.EstadoIncidencia;
import models.Incidencia;
import repository.IIncidenciaRepository;
import services.usecases.exceptions.IncidenciaNoEncontradaException;
import services.usecases.exceptions.TransicionEstadoInvalidaException;

public class CambiarEstadoIncidencia {
    private final IIncidenciaRepository<Incidencia> incidenciaRepository;

    public CambiarEstadoIncidencia(IIncidenciaRepository<Incidencia> incidenciaRepository){
        this.incidenciaRepository = incidenciaRepository;

    }

    public Incidencia ejecutar(Incidencia incidencia, EstadoIncidencia nuevoEstado) {

        if (incidencia == null) {
            throw new IncidenciaNoEncontradaException("Incidencia no puede ser nula");
        }

        EstadoIncidencia estadoActual = incidencia.getEstadoActual();

        //Logica de validacion estricta de la transicion de estados:

        boolean transicionValida = false;

        if (estadoActual == EstadoIncidencia.PENDIENTE && nuevoEstado == EstadoIncidencia.EN_PROCESO) {
            transicionValida = true;
        }
        else if (estadoActual == EstadoIncidencia.EN_PROCESO && nuevoEstado == EstadoIncidencia.RESUELTA) {
            transicionValida = true;
        }

        if (!transicionValida) {
            throw new TransicionEstadoInvalidaException(estadoActual, nuevoEstado);
        }

        //si es valida , asignamos el nuevo estado
        incidencia.setEstadoActual(nuevoEstado);

        //se guarda y devuelve la incidencia actualiazada
        return incidenciaRepository.save(incidencia);
    }

}
