package repository;

import models.Incidencia;

import java.util.ArrayList;
import java.util.List;

public class IncidenciaInMemory implements IIncidenciaRepository<Incidencia> {

    List<Incidencia> incidencias;

    public IncidenciaInMemory(){
        incidencias = new ArrayList<>();
    }


    @Override
    public Incidencia save(Incidencia incidencia) {
        if (incidencia != null) {
            if (!incidencias.contains(incidencia)) {
                incidencias.add(incidencia);
            }
            return incidencia;
        }
        return null;
    }

    @Override
    public List<Incidencia> ListAll() {
        return incidencias;
    }

    @Override
    public Incidencia findById(String id) {
        for (Incidencia inc : incidencias) {
            if(inc.getIdentificador().equals(id)){
                return inc;
            }
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        Incidencia inc = findById(id);
        incidencias.remove(inc);
    }

    @Override
    public boolean existsById(String id) {
        return findById(id) != null;
    }
}
