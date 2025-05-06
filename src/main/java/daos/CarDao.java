package daos;

import com.sun.jdi.connect.Connector;
import models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarDao implements Dao<Car> {


    public Car findCarById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Car2 WHERE id=" + id);
            if (rs.next()) {
                return extractCarFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public List<Car> findAll() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Car2");
            List cars = new ArrayList<Car>();
            while (rs.next()) {
                Car car  = extractCarFromResultSet(rs);
                cars.add(car);
            }
            return cars;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Car create(Car car) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Car2 VALUES ( ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, car.getId());
            ps.setString(2, car.getMake());
            ps.setString(3, car.getModel());
            ps.setInt(4, car.getYear());
            ps.setString(5,car.getVin());
            ps.setString(6,car.getColor());
            int i = ps.executeUpdate();
            if(i == 1) {
                return car;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public Car update(Car car) {
        Connection connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE Car2 SET make=?, model=?, year=?, vin=?, color=? WHERE id=?");

                ps.setString(1, car.getMake());
                ps.setString(2, car.getModel());
                ps.setInt(3, car.getYear());
                ps.setString(4,car.getVin());
                ps.setString(5,car.getColor());
                ps.setInt(6, car.getId());
                int i = ps.executeUpdate();
                if(i == 1) {
                    return car;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return null;
        }


    public void delete(int id) {
            Connection connection = ConnectionFactory.getConnection();
            try {
                Statement stmt = connection.createStatement();
                int i = stmt.executeUpdate("DELETE FROM Car2 WHERE id=" + id);


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }


    private Car extractCarFromResultSet(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setMake(rs.getString("make"));
        car.setModel(rs.getString("model"));
        car.setColor(rs.getString("color"));
        car.setYear(rs.getInt("year"));
        car.setVin(rs.getString("vin"));
        return car;
    }
}
