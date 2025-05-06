package daos;

import com.sun.org.apache.xpath.internal.operations.Bool;
import models.Car;

import java.util.List;

public interface Dao<T> {
    T findCarById(int id);
    List<T> findAll();
    T update(Car dto);
    T create(Car dto);
    void delete (int id);
}
