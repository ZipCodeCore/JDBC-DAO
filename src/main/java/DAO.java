import java.util.List;
public interface DAO<X> {
    public X findById(int id);

    public List findAll();

    public X update(X dto);

    public X create(X dto);

    public void delete(int id);
}