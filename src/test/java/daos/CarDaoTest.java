package daos;

import models.Car;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CarDaoTest {
    private CarDao carDao;

    @Before
    public void setUp() throws Exception {
        this.carDao = new CarDao();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findCarByIdTest() {
        String expectedVin ="WAUVT68E33A130589";
        String actualVin  = carDao.findCarById(1).getVin();
        Assert.assertEquals(expectedVin,actualVin);
    }

    @Test
    public void findAllTest() {
        List<Car> cars = carDao.findAll();
        Integer actual = cars.size();
        Integer expected = 10;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void createTest() {

        Car car = new Car (11, "Chevy", "Cobalt", 2017, "Silver", "1LMN70HJI55677Y15");
        Car car2 = carDao.create(car);
        int actual = car2.getId();
        int expected = 11;
        carDao.delete(11);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void updateTest() {
        Car car = carDao.findCarById(2);
        car.setColor("Brown");
        Car car2 = carDao.update(car);
        String actual = car2.getColor();
        String expected = "Brown";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void deleteTest() {
        Car car = new Car (12, "Chevy", "Cobalt", 2017, "Silver", "1LMN70HJI55677Y15");
        Car car2 = carDao.create(car);
        List<Car>cars=carDao.findAll();
        int actual = cars.size();
        carDao.delete(12);
        int expected = carDao.findAll().size();
        Assert.assertEquals(expected, actual-1);

    }
}