package daos;

import models.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepo<T extends Car> implements DAO<T>, ConnectionInterface {

    private Connection connection;

    public CarRepo(Connection connection) {
        this.connection = connection;
    }

    public T findById(int id) {
        return (T) findAll()
                .stream()
                .filter(car -> car.getId() == (id))
                .findAny()
                .get();
    }

    public List<Car> findAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM zcwdblabs.car;");
        List<Car> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                String year = resultSet.getString(4);
                String color = resultSet.getString(5);
                String vin = resultSet.getString(6);
                list.add(new Car(
                        Integer.parseInt(id),
                        make,
                        model,
                        Integer.parseInt(year),
                        color,
                        Integer.parseInt(vin)));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return list;
    }

    public void update(int carId, Car dataToUpdate) {
        executeStatement(String.format(new StringBuilder()
                .append("UPDATE car SET make = '%s',")
                .append("model = '%s',")
                .append("year = %s,")
                .append("color = '%s',")
                .append("vin = %s ")
                .append("WHERE id = %s;")
                .toString(),
                dataToUpdate.getMake(),
                dataToUpdate.getModel(),
                dataToUpdate.getYear(),
                dataToUpdate.getColor(),
                dataToUpdate.getVin(),
                carId));
    }

    public void create(T dataToCreate) {
        executeStatement(String.format(new StringBuilder()
                .append("INSERT INTO car(")
                .append("id, make, model, year, color, vin) ")
                .append("VALUES (%s, '%s', '%s', %s, '%s', %s);")
                .toString(),
                dataToCreate.getId(),
                dataToCreate.getMake(),
                dataToCreate.getModel(),
                dataToCreate.getYear(),
                dataToCreate.getColor(),
                dataToCreate.getVin()));
    }

    public void delete(int id) {
        executeStatement(String.format(new StringBuilder()
                .append("DELETE FROM zcwdblabs.car WHERE id = %s")
                .toString(),
                id));
    }


    @Override
    public Connection getConnection() {
        return connection;
    }
}
