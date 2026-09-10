package services.usecases;

import models.Incidencia;
import repository.IIncidenciaRepository;

public class RegistrarIncidencia {
//    validación de vacíos + generación de ID + guardado
    private final IIncidenciaRepository<Incidencia> incidenciaRepository;
    public RegistrarIncidencia(IIncidenciaRepository<Incidencia> incidenciaRepository){
        this.incidenciaRepository = incidenciaRepository;
    }
}
