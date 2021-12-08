package daos;

import java.util.List;

public abstract class BaseDao<T> {

    public abstract T findById(int id);
    public abstract List findAll();
    public abstract T update(T dto);
    public abstract T create(T dto);
    public abstract void delete(int id);
}
