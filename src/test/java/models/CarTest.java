package models;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarTest {


    @Test
    public void nullaryConstructorTest() {
        Car car = new Car();
        Integer expected = 0;

        Integer actual = car.getId();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void constructorWithoutIDTest() {
        Car car = new Car("'LilBabyMobile'", "asdf",
                41, "ca", 14);
        String expectedMake = "'LilBabyMobile'";
        Integer expectedId = 0;

        String actualMake = car.getMake();
        Integer actualId = car.getId();

        Assert.assertEquals(expectedMake, actualMake);
        Assert.assertEquals(expectedId, actualId);
    }

    @Test
    public void fullConstructorTest() {
        Car car = new Car(10, "Toyoda", "primeski",
                12, "yellow", 123 );
        Integer expectedId = 10;
        String expectedMake = "Toyoda";
        String expectedModel = "primeski";
        Integer expectedYear = 12;
        String expectedColor = "yellow";
        Integer expectedVin = 123;

        Integer actualId = car.getId();
        String actualMake = car.getMake();
        String actualModel = car.getModel();
        Integer actualYear = car.getYear();
        String actualColor = car.getColor();
        Integer actualVin = car.getVin();

        Assert.assertEquals(expectedId, actualId);
        Assert.assertEquals(expectedMake, actualMake);
        Assert.assertEquals(expectedModel, actualModel);
        Assert.assertEquals(expectedYear, actualYear);
        Assert.assertEquals(expectedColor, actualColor);
        Assert.assertEquals(expectedVin, actualVin);
    }
}
