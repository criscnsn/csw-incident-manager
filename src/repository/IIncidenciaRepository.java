package repository;

import java.util.List;

public interface IIncidenciaRepository<T>{
    // CRUD
    T save(T t);

    List<T> ListAll();
    T findById(String id);

    void deleteById(String id);
    boolean existsById(String id);
}
