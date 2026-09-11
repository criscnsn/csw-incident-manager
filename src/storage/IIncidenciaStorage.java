package storage;

import models.Incidencia;
import java.util.List;

public interface IIncidenciaStorage {
    Incidencia save(Incidencia incidencia);

    List<Incidencia> listAll();
    Incidencia findById(String identificador);

    void deleteById(String identificador);
    boolean existsById(String identificador);
}
