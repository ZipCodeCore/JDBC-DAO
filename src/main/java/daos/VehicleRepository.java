package daos;

import models.Vehicle;

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
public class VehicleRepository implements Repo {
    private Connection connection;

    public VehicleRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public void create(Vehicle vehicle) {
        executeStatement(String.format(new StringBuilder()
                        .append("INSERT INTO model.car(")
                        .append("id, make, model, year, color, vin) ")
                        .append("VALUES (%s, '%s', '%s', %s, '%s', '%s');")
                        .toString(),
                vehicle.getId(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getColor(),
                vehicle.getVin()));
    }

    public List<Vehicle> readAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM model.car;");
        List<Vehicle> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                String year = resultSet.getString(4);
                String color = resultSet.getString(5);
                String vin = resultSet.getString(6);
                list.add(new Vehicle(
                        Long.parseLong(id),
                        make,
                        model,
                        Integer.parseInt(year),
                        color,
                        vin));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return list;
    }

    public Vehicle findById(Long vehicleId) {
        return readAll()
                .stream()
                .filter(pokemon -> pokemon.getId().equals(vehicleId))
                .findAny()
                .get();
    }

    public void update(Long id, Vehicle newCar) {
        executeStatement(String.format(new StringBuilder()
                .append("UPDATE car SET make = '%s', ")
                .append("model = '%s', ")
                .append("year = %s, ")
                .append("color = '%s', ")
                .append("vin = '%s' ")
                .append("WHERE primaryId = %s;")
                .toString(),

                newCar.getMake(),
                newCar.getModel(),
                newCar.getYear(),
                newCar.getColor(),
                newCar.getVin(),
                id));
    }

    public void delete(Long id) {
        executeStatement(String.format(new StringBuilder()
                .append("DELETE FROM car ")
                .append("WHERE primaryId = %s;")
                .toString(),
                id));
    }

    public void delete(Vehicle car) {
        delete(car.getId());
    }

}
