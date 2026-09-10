package services.usecases;

import models.Incidencia;
import repository.IIncidenciaRepository;

public class ListarIncidencias {
//    recuperación + formato de presentación
    private final IIncidenciaRepository<Incidencia> incidenciaRepository;
    public ListarIncidencias(IIncidenciaRepository<Incidencia> incidenciaRepository){
        this.incidenciaRepository = incidenciaRepository;
    }
}
