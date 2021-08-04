package models;


import org.junit.Assert;
import org.junit.Test;

public class testVehicle {

    @Test
    public void testVehicleMake(){
        Vehicle vehicle = new Vehicle(20L, "Jenson", "Wagner", "Red", "Three Wheeler", 20);
        String expected = "Jenson";
        String actual = vehicle.getMake();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testVehicleModel(){
        Vehicle vehicle = new Vehicle(20L, "Jenson", "Wagner", "Red", "Three Wheeler", 20);
        String expected = "Wagner";
        String actual = vehicle.getModel();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testVehicleColor(){
        Vehicle vehicle = new Vehicle(20L, "Jenson", "Wagner", "Red", "Three Wheeler", 20);
        String expected = "Red";
        String actual = vehicle.getColor();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testVehicleVType(){
        Vehicle vehicle = new Vehicle(20L, "Jenson", "Wagner", "Red", "Three Wheeler", 20);
        String expected = "Three Wheeler";
        String actual = vehicle.getvType();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testVehicleMpg(){
        Vehicle vehicle = new Vehicle(20L, "Jenson", "Wagner", "Red", "Three Wheeler", 20);
        Integer expected = 20;
        Integer actual = vehicle.getMpg();

        Assert.assertEquals(expected, actual);
    }
}
