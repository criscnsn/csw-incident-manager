package repository;

import java.util.List;

public interface IIncidenciaRepository<T>{
    // CRUD
    T save(T t);

    List<T> ListAll();
    T findById(int id);

    void deleteById(int id);
    boolean existsById(int id);
}
