package daos;

import com.sun.deploy.net.MessageHeader;
import models.Cars;

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
public class CarsRepository implements Repo {
    private Connection connection;

    public CarsRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public void create(Cars cars) {
        executeStatement(String.format(new StringBuilder()
                        .append("INSERT INTO Objects.cars(")
                        .append("id, make, year, color, MPG) ")
                        .append("VALUES (%s, '%s', %s, '%s', %s);")
                        .toString(),
                cars.getId(),
                cars.getMake(),
                cars.getYear(),
                cars.getColor(),
                cars.getMPG()));
    }

    public List<Cars> readAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM Objects.cars;");
        List<Cars> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String make = resultSet.getString(2);
                String year = resultSet.getString(3);
                String color = resultSet.getString(4);
                String MPG = resultSet.getString(5);
                list.add(new Cars(
                        Long.parseLong(id),
                        make,
                        Integer.parseInt(year),
                        color,
                        Integer.parseInt(MPG)));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return list;
    }

    public Cars read(Long id) {
        return readAll()
                .stream()
                .filter(pokemon -> pokemon.getId().equals(id))
                .findAny()
                .get();
    }

    public void update(Long id, Cars newCar) {
        executeStatement(String.format(new StringBuilder()
        .append("Update cars Set make = '%s', ")
        .append("year = '%s', ")
        .append("color = '%s', ")
        .append("MPG = '%s', ")
        .toString(),

                newCar.getMake(),
                newCar.getYear(),
                newCar.getColor(),
                newCar.getMPG(),
                id));


    }

    public void delete(Long id) {
        executeStatement(String.format(new StringBuilder()
        .append("DELETE FROM cars ")
        .append("WHERE primaryID = %s; ")
        .toString(),
                id));

    }

    public void delete(Cars cars) {
        delete(cars.getId());
    }

}
