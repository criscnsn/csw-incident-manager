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
     * Si hay incidencias registradas, devuelve una vista no modificable (Collections unmodifiableList)
     * Si por alguna razón el repositorio fuera null, devuelve una Lista vacía
     *
     * NOTA: Si es muy obvio, pues borren esto PLOx
     * */
    public List<Incidencia> ejecutar() {
        final List<Incidencia> incidencias = this.incidenciaRepository.ListAll();
        return (incidencias != null) ? Collections.unmodifiableList(incidencias) : List.of();
    }
}

