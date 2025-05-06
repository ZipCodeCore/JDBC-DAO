package daos;


import java.util.Set;

public interface DAO<E> {
    E findById(Integer id);
    Set<E> findAll();
    Boolean insert(E obj);
    Boolean update(Integer id, E obj);
    Boolean delete(Integer id);
}
