package daos;

import models.Car;
import models.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Car_Dao implements Dao <Car>{

    Connection connection = ConnectionFactory.getConnection();

    public Car extractCarFromSQL(ResultSet rs) throws SQLException {
        Car car = new Car();

        car.setId(((ResultSet) rs).getInt(1));
        car.setMake(((ResultSet) rs).getString("Nissan"));
        car.setModel(((ResultSet) rs).getString("Maxima"));
        car.setColor(((ResultSet) rs).getString("White"));
        car.setYear(((ResultSet) rs).getInt(2017));

        return car;
    }


    public Car findByID(Integer id) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM car WHERE id=" + id);

            if (((ResultSet) rs).next()) {
                return extractCarFromSQL(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Set findAll() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM car");

            Set carSet = new HashSet();

            while (rs.next()) {
                Car car = extractCarFromSQL(rs);
                carSet.add(car);
            }

            return carSet;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Boolean update(Car car) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE user SET id=?, make=?, model=? color=?, year=?");
            if (preparedStatement(car, ps)) return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    public Boolean create(Car car) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?)");
            if (preparedStatement(car, ps)) return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean delete(Integer id) {
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM videogames WHERE id=" + id);
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    private boolean preparedStatement(Car car, PreparedStatement ps) throws SQLException {
        ps.setInt(1, car.getId());
        ps.setString(2, car.getMake());
        ps.setString(3, car.getModel());
        ps.setString(4, car.getColor());
        ps.setInt(5, car.getYear());
        int i = ps.executeUpdate();

        if (i == 1) {
            return true;
        }
        return false;
    }

}
