import com.sun.xml.internal.bind.v2.model.core.ID;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCarsDAO {

    @Test
    public void testFindById(){

        CarsDAO c1 = new CarsDAO();
        CarsDTO a1 = c1.findById(1);
        CarsDTO e1 = new CarsDTO(1 , "Nissan" , "Frontier" , "2019" , "white" , "1392BR568");
       /* Assert.assertEquals(a1.getID(),e1.getID());
        Assert.assertEquals(a1.getCOLOR(), e1.getCOLOR());
        Assert.assertEquals(a1.getMAKE() , e1.getMAKE());
        Assert.assertEquals(a1.getMODEL() , e1.getMODEL());
        Assert.assertEquals(a1.getYear() , e1.getYear());
        Assert.assertEquals(a1.getVIN() , e1.getVIN());*/
        Assert.assertEquals(a1.toString(),e1.toString());
    }
    @Test
    public void testFindAll(){
        CarsDAO c1 = new CarsDAO();
        List carList = new ArrayList();
        carList= c1.findAll();
        System.out.println(carList);
    }



    }

