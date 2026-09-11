package repository;

import java.util.List;

public interface IIncidenciaRepository<T> {
    T save(T entidad);

    List<T> listAll();
    T findById(String identificador);

    void deleteById(String identificador);
    boolean existsById(String identificador);
}
