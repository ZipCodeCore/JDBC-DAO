package daos;

import models.Vehicle;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class RepoTest {
    @Test
    public void testCreate() {
        // Given
        Vehicle car = new Vehicle(7L, "Tesla", "Model X", 2021, "Matte Black", "1FTFW1EF9DKE31717");
        String expected = car.toString();

        // When
        Connection connection = main.SQLConnector.getConnection("mysql");
        VehicleRepository vehicleRepository = new VehicleRepository(connection);
        vehicleRepository.create(car);
        Vehicle car1 = vehicleRepository.findById(7L);
        String actual = car1.toString();

        // Then
        Assert.assertEquals(expected, actual);
        vehicleRepository.delete(7L);
    }

    @Test
    public void testFindAll() {

    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testDelete() {

    }

}
