package daos;

import java.util.Set;

public interface Dao <T> {
    public T findByID(Integer id);

    public Set findAll();

    public Boolean update(T dto);

    public Boolean create(T dto);

    public Boolean delete(Integer id);

}

