import daos.CarDao;
import models.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MainExecutor {

    public static final String URL = "jdbc:mysql://localhost:3306/zipcode";
    public static final String USER = "keer";
    public static final String PASS = "keer123";

    public static void main(String[] args) {

//        try {
//            Class.forName("com.mysql.jdbc.driver");
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
            //TRY RESOURCE METHOD
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)){

            CarDao carDao = new CarDao(connection);

            List<Car> cars = carDao.findAll();
            int noOfCars = cars.size();
            System.out.println("No of Cars available in database: " + noOfCars);
            cars.forEach(System.out::println);

            Car foundCar = carDao.findById(cars.get(1).getId());
            System.out.println("Find Car by ID : " + cars.get(1).getId() + " => Model: " + foundCar.getMake() + " Make: " + foundCar.getModel() + " VIN: " + foundCar.getVin());

            Car newCar = getCar(++noOfCars, "Audi", "R8", 2020, "yellow", 154323456);
            Car createdCar = carDao.create(newCar);
            System.out.println("New Car created ID : " + createdCar.getId() + " => Model: " + createdCar.getMake() + " Make: " + createdCar.getModel() + " VIN: " + createdCar.getVin());

            foundCar.setVin(foundCar.getVin()+100);
            Car updatedCar = carDao.update(foundCar);
            System.out.println("Updated Car ID : " + updatedCar.getId() + " => Model: " + updatedCar.getMake() + " Make: " + updatedCar.getModel() + " VIN: " + updatedCar.getVin());

            List<Car> carsBeforeDelete = carDao.findAll();
            int noOfCarsBeforeDelete = carsBeforeDelete.size();
            System.out.println("No of Cars available before delete operation: " + noOfCarsBeforeDelete);

            carDao.delete(noOfCars);
            System.out.println("Successfully deleted car. ID: " + noOfCars);

            List<Car> carsAfterDelete = carDao.findAll();
            int noOfCarsAfterDelete = carsAfterDelete.size();
            System.out.println("No of Cars available after delete operation: " + noOfCarsAfterDelete);

        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public static Car getCar(int id, String make, String model, int year, String color, long vin) {
        Car car = new Car();
        car.setId(id);
        car.setModel(model);
        car.setMake(make);
        car.setYear(year);
        car.setColor(color);
        car.setVin(vin);
        return car;
    }
}
