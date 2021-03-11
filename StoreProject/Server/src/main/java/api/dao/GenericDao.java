package api.dao;

import java.util.List;

public interface GenericDao<T> {

    void create(T entity);

    T getById(int id);

    List<T> getAll();

    void delete(T entity);

    T update(T entity);
}
