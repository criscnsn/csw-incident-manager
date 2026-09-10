package services.usecases;

import models.Incidencia;
import repository.IIncidenciaRepository;

public class RegistrarIncidencia {
    private final IIncidenciaRepository<Incidencia> incidenciaRepository;
    public RegistrarIncidencia(IIncidenciaRepository<Incidencia> incidenciaRepository){
        this.incidenciaRepository = incidenciaRepository;
    }
}
