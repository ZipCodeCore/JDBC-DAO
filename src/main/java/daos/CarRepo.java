package daos;

import models.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author git-leon
 * @version 1.0.0
 * @date 8/4/21 3:05 PM
 */
public class CarRepo implements Repo {
    private Connection connection;

    public CarRepo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public void create(Car car) {
        executeStatement(String.format(new StringBuilder()
                        .append("INSERT INTO daolab.vehicle(")
                        .append("primaryId, make, model, color, vtype, mpg) ")
                        .append("VALUES (%s, '%s', '%s', '%s', '%s', %s);")
                        .toString(),
                car.getId(),
                car.getMake(),
                car.getModel(),
                car.getColor(),
                car.getvType(),
                car.getMpg()));
    }

    public List<Car> readAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM daolab.vehicle;");
        List<Car> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                String color = resultSet.getString(4);
                String vtype = resultSet.getString(5);
                String mpg = resultSet.getString(6);
                list.add(new Car(
                        Long.parseLong(id),
                        make,
                        model,
                        color,
                        vtype,
                        Integer.parseInt(mpg)));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return list;
    }

    public Car read(Long primaryId) {
        return readAll()
                .stream()
                .filter(car -> car.getId().equals(primaryId))
                .findAny()
                .get();
    }

    public void update(Long id, Car newCarData) {
        executeStatement(String.format(new StringBuilder()
                        .append("UPDATE vehicle SET make= '%s',")
                        .append("model = '%s',")
                        .append("color = '%s',")
                        .append("vtype = '%s',")
                        .append("mpg = %s ")
                        .append("WHERE primaryId = %s;")
                        .toString(),

                newCarData.getMake(),
                newCarData.getModel(),
                newCarData.getColor(),
                newCarData.getvType(),
                newCarData.getMpg(),
                id));
    }

    public void delete(Long id) {
        executeStatement(String.format(new StringBuilder()
                        .append("DELETE FROM vehicle ")
                        .append("WHERE primaryId = %s;")
                        .toString(),
                id
        ));
    }

    public void delete(Car car) {
        delete(car.getId());
    }

}