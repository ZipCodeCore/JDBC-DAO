package daos;

import models.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class CarRepoTest implements ConnectionInterface {

    private Connection connection;

    @Test
    public void createDOATest () {
//        CarRepo carRepo = new CarRepo();
//        Integer expected = 2;
//        Car car = new Car();
//        Car car1 = new Car();
//        Car car2 = new Car();
//        carRepo.create(car);
//        carRepo.create(car1);
//        carRepo.create(car2);
//        Integer actual = carRepo.findAll().size();
//
//        Assert.assertEquals(expected, actual);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
