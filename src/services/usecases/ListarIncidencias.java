package services.usecases;

import models.Incidencia;
import repository.IIncidenciaRepository;

import java.util.Collections;
import java.util.List;

public class ListarIncidencias {

    private final IIncidenciaRepository<Incidencia> incidenciaRepository;

    public ListarIncidencias(final IIncidenciaRepository<Incidencia> incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }
    /**
     * Devuelve una lista de solo lectura con todas las incidencias registradas.
     * Si no hay incidencias o el repositorio devuelve null, retorna una lista vacía inmutable.
     */
    public List<Incidencia> ejecutar() {
        final List<Incidencia> incidencias = this.incidenciaRepository.listAll();
        return (incidencias != null) ? Collections.unmodifiableList(incidencias) : List.of();
    }
}

