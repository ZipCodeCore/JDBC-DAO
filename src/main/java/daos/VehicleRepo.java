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
public class VehicleRepo implements Repo {
    private Connection connection;

    public VehicleRepo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public void create(Vehicle vehicle) {
        executeStatement(String.format(new StringBuilder()
                        .append("INSERT INTO daolab.vehicle(")
                        .append("primaryId, make, model, color, vtype, mpg) ")
                        .append("VALUES (%s, '%s', '%s', '%s', '%s', %s);")
                        .toString(),
                vehicle.getId(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getvType(),
                vehicle.getMpg()));
    }

    public List<Vehicle> readAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM daolab.vehicle;");
        List<Vehicle> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                String color = resultSet.getString(4);
                String vtype = resultSet.getString(5);
                String mpg = resultSet.getString(6);
                list.add(new Vehicle(
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

    public Vehicle read(Long primaryId) {
        return readAll()
                .stream()
                .filter(vehicle -> vehicle.getId().equals(primaryId))
                .findAny()
                .get();
    }

    public void update(Long id, Vehicle newVehicleData) {
        executeStatement(String.format(new StringBuilder()
                        .append("UPDATE vehicle SET make= '%s',")
                        .append("model = '%s',")
                        .append("color = '%s',")
                        .append("vtype = '%s',")
                        .append("mpg = %s ")
                        .append("WHERE primaryId = %s;")
                        .toString(),

                newVehicleData.getMake(),
                newVehicleData.getModel(),
                newVehicleData.getColor(),
                newVehicleData.getvType(),
                newVehicleData.getMpg(),
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

    public void delete(Vehicle vehicle) {
        delete(vehicle.getId());
    }

}
