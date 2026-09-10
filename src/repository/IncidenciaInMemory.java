package repository;

import models.Incidencia;

import java.util.List;

public class IncidenciaInMemory implements IncidenciaRepository<Incidencia>{


    @Override
    public Incidencia save(Incidencia incidencia) {
        return null;
    }

    @Override
    public List<Incidencia> ListAll() {
        return List.of();
    }

    @Override
    public Incidencia findById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public boolean existsById(int id) {
        return false;
    }
}
