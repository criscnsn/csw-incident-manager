package services.usecases;

import models.Incidencia;
import repository.IIncidenciaRepository;

public class BuscarIncidenciaPorId {
//    búsqueda + manejo de "no encontrado"
    private final IIncidenciaRepository<Incidencia> incidenciaRepository;
    public BuscarIncidenciaPorId(IIncidenciaRepository<Incidencia> incidenciaRepository){
        this.incidenciaRepository = incidenciaRepository;
    }
}
