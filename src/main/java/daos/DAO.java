package daos;

import java.util.Set;

public interface DAO<T> {
    T findById(Integer id);
    Set<T> findAll();
    Boolean update(Integer id, T object);
    Boolean create(T object);
    Boolean delete(Integer id);
}
