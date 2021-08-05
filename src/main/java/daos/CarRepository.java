package daos;

import com.sun.deploy.net.MessageHeader;
import models.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CarRepository implements Repo {
    private Connection connection;

    public CarRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public void create(Car car) {
        executeStatement(String.format(new StringBuilder()
                        .append("INSERT INTO carDatabase.carTable(")
                        .append("id, make, model, year, color, vin) ")
                        .append("VALUES (%s, '%s', '%s', %s, '%s', %s);")
                        .toString(),
                car.getId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getColor(),
                car.getVin()));
                
    }

    public List<Car> readAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM carDatabase.carTable;");
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
                        Long.parseLong(id),
                        make,
                        model,
                        Integer.parseInt(year),
                        color,
                        Long.parseLong(vin)));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return list;
    }

    public Car read(Long Carid) {
        return readAll()
                .stream()
                .filter(car -> car.getId().equals(Carid))
                .findAny()
                .get();
    }

    public void update(Long id, Car newPokemonData) {
        executeStatement(String.format(new StringBuilder()
             .append("UPDATE carTable,")
                .append("SET make '%s',")
                .append("model '%s',")
                .append("year %s,")
                .append("color '%s',")
                .append("vin %s,")
                .append("WHERE id = %s;")
                .toString(),
                newPokemonData.getMake(),
                newPokemonData.getModel(),
                newPokemonData.getYear(),
                newPokemonData.getColor(),
                newPokemonData.getVin(),
                id));

    }

    public void delete(Long id) {
        executeStatement(String.format(new StringBuilder()
                .append("DELETE FROM carTable WHERE id = %s")
                .toString(),
                id));
    }

    public void delete(Car car) {
        delete(car.getId());
    }

}