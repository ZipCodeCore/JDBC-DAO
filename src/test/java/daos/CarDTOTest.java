package daos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarDTOTest {
    DAOConcrete daoConcrete;
    CarDTO car1, car2, car3;

    @Before
    public void setUp() throws Exception {
    daoConcrete = new DAOConcrete();
    car1 = new CarDTO(6, "Oldsmobile", "Cutlass",1987, "Black", "1987OC");
    car2 = new CarDTO(5, "Pontiac", "Firebird", 1981, "Yellow", "1981PF");
    car3 = new CarDTO(2, "Pontiac", "Lemans", 1969, "Gold", "1969PL");
    }

    @Test
    public void testFindById () {
        String expected = "Car ID: 6, Make: Oldsmobile, Model: Cutlass, Year: 1987, Color: Black, VIN: 1987OC";
        String actual = daoConcrete.findById(6).toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindAll () {
        Integer expected = 6;
        Integer actual = daoConcrete.findAll().size();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testCreate () {
        String expected = "Car ID: 7, Make: Toyota, Model: Echo, Year: 2001, Color: Green, VIN: 2001TE";
        CarDTO carToCreate = new CarDTO(7, "Toyota", "Echo", 2001, "Green", "2001TE");
        String actual = daoConcrete.create(carToCreate);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdate () {
        String expected = "Car ID: 7, Make: Toyota, Model: Echo, Year: 2001, Color: Green Patina, VIN: 2001TE";
        CarDTO carToModify = new CarDTO(7, "Toyota", "Echo", 2001, "Green Patina", "2001TE");
        String actual = daoConcrete.update(carToModify);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDelete () {
        Boolean expected = true;
        Boolean actual = daoConcrete.delete(7);
        Assert.assertEquals(expected, actual);
    }

}
