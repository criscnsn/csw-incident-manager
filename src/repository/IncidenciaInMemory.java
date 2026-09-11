package repository;

import models.Incidencia;

import java.util.ArrayList;
import java.util.List;

public class IncidenciaInMemory implements IIncidenciaRepository<Incidencia> {

    private final List<Incidencia> incidencias;

    public IncidenciaInMemory() {
        this.incidencias = new ArrayList<>();
    }

    @Override
    public Incidencia save(final Incidencia incidencia) {
        if (incidencia != null) {
            if (!this.incidencias.contains(incidencia)) {
                this.incidencias.add(incidencia);
            }
            return incidencia;
        }
        return null;
    }

    @Override
    public List<Incidencia> listAll() {
        return this.incidencias;
    }

    @Override
    public Incidencia findById(final String identificador) {
        for (final Incidencia inc : this.incidencias) {
            if (inc.getIdentificador().equals(identificador)) {
                return inc;
            }
        }
        return null;
    }

    @Override
    public void deleteById(final String identificador) {
        final Incidencia inc = findById(identificador);
        this.incidencias.remove(inc);
    }

    @Override
    public boolean existsById(final String identificador) {
        return findById(identificador) != null;
    }
}
