package daos;

import com.sun.jdi.connect.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DataAccessObject<T> implements PatternOps<T>{


    public T findById(int id) {
        return null;
    }

    public List<T> findAll() {
        return null;
    }

    public T update(T dataToUpdate) {
        return null;
    }

    public T create(T dataToCreate) {
        return null;
    }

    public void delete(int id) {

    }
}
