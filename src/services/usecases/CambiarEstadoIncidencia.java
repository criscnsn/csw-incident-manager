package services.usecases;

import models.Incidencia;
import repository.IIncidenciaRepository;

public class CambiarEstadoIncidencia {
    private final IIncidenciaRepository<Incidencia> incidenciaRepository;
    public CambiarEstadoIncidencia(IIncidenciaRepository<Incidencia> incidenciaRepository){
        this.incidenciaRepository = incidenciaRepository;
    }
}
