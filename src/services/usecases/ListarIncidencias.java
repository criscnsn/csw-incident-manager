package services.usecases;

import models.Incidencia;
import storage.IIncidenciaStorage;

import java.util.Collections;
import java.util.List;

public class ListarIncidencias {

    private final IIncidenciaStorage incidenciaStorage;

    public ListarIncidencias(final IIncidenciaStorage incidenciaStorage) {
        this.incidenciaStorage = incidenciaStorage;
    }
    /**
     * Devuelve una lista de solo lectura con todas las incidencias registradas.
     * Si no hay incidencias o el almacenamiento devuelve null, retorna una lista vacía inmutable.
     */
    public List<Incidencia> ejecutar() {
        final List<Incidencia> incidencias = this.incidenciaStorage.listAll();
        return (incidencias != null) ? Collections.unmodifiableList(incidencias) : List.of();
    }
}

