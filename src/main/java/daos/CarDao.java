package daos;

import models.Car;
import models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDao extends BaseDao<Car> {

    private Connection connection;

    public CarDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * This method is used to find Car by id
     * @param id
     * @return Car
     */
    public Car findById(int id) {

        Car car = null;
        try (PreparedStatement ps = connection.prepareStatement("Select id,make,model,year,color,vin from Car where id = ?")) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                car = new Car();
                car.setId(rs.getInt(1));
                car.setMake(rs.getString(2));
                car.setModel(rs.getString(3));
                car.setYear(rs.getInt(4));
                car.setColor(rs.getString(5));
                car.setVin(rs.getLong(6));
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return car;
    }

    /**
     *
     * @return
     */
    public List findAll() {
        List<Car> carList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("Select id,make,model,year,color,vin from Car order by id")) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Car car = new Car();
                car.setId(rs.getInt(1));
                car.setMake(rs.getString(2));
                car.setModel(rs.getString(3));
                car.setYear(rs.getInt(4));
                car.setColor(rs.getString(5));
                car.setVin(rs.getLong(6));
                carList.add(car);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return carList;
    }

    /**
     *
     * @param car
     * @return
     */
    public Car update(Car car) {

        try (PreparedStatement ps = connection.prepareStatement("update car set make=?,model=?,year=?,color=?,vin=? where id =?")) {

            ps.setString(1, car.getMake());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYear());
            ps.setString(4, car.getColor());
            ps.setLong(5, car.getVin());
            ps.setInt(6, car.getId());

            boolean status = ps.execute();
            if (status) {
                System.out.println("Successfully Updated Car Data");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return car;
    }

    /**
     *
     * @param car
     * @return
     */
    public Car create(Car car) {

        try (PreparedStatement ps = connection.prepareStatement("insert into Car(id,make,model,year,color,vin) values(?,?,?,?,?,?)")) {


            ps.setInt(1, car.getId());
            ps.setString(2, car.getMake());
            ps.setString(3, car.getModel());
            ps.setInt(4, car.getYear());
            ps.setString(5, car.getColor());
            ps.setLong(6, car.getVin());

            boolean status = ps.execute();
            if (status) {
                System.out.println("Successfully Inserted Car Data");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return car;
    }

    /**
     *
     * @param id
     */
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement("delete from car where id = ?")) {

            ps.setInt(1, id);

            boolean status = ps.execute();
            if (status) {
                System.out.println("Successfully Delete Car Data");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
