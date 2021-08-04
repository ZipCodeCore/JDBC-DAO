package daos;

import models.Car;

import java.util.List;

public interface DAO<T> {

    public T findById(int id);

    public List<Car> findAll();

    public void update(int carId, Car dataToUpdate);

    public void create(T dataToCreate);

    public void delete(int id);
}
