package services.usecases;

import models.Incidencia;
import repository.IIncidenciaRepository;

import java.util.List;

public class ListarIncidencias {

    private final IIncidenciaRepository<Incidencia> incidenciaRepository;

    public ListarIncidencias(final IIncidenciaRepository<Incidencia> incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    public List<Incidencia> ejecutar() {
        return this.incidenciaRepository.ListAll();
    }
}

