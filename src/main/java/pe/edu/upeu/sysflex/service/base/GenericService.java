package pe.edu.upeu.sysflex.service.base;

import java.util.List;

public interface GenericService<T, ID> {
    List<T> findAll();
    T findById(ID id);
    T save(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}