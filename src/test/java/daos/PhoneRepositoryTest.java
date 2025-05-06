package daos;

import com.mysql.cj.jdbc.Driver;
import models.Phone;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.NoSuchElementException;

public class PhoneRepositoryTest {

    @Test
    public void readTest(){
        //given
        Engine engine = new Engine();
        engine.registerJDBCDriver();
        Connection mysqlDbConnection = engine.getConnection("mysql");
        PhoneRepository phoneRepository = new PhoneRepository(mysqlDbConnection);
        engine.executeStatement(mysqlDbConnection, "USE walmart;");

        String expectedName = "FlipidyFlip";
        phoneRepository.create(new Phone(22, expectedName, "'grey'", "'sprint'", 5, 699.0));
        //when
        Phone retrievedPhone = phoneRepository.read(22);
        String actual = retrievedPhone.getName();
        //then
        Assert.assertEquals(expectedName,actual);
    }

    @Test
    public void updateTest(){
        //given
        Engine engine = new Engine();
        engine.registerJDBCDriver();
        Connection mysqlDbConnection = engine.getConnection("mysql");
        PhoneRepository phoneRepository = new PhoneRepository(mysqlDbConnection);
        engine.executeStatement(mysqlDbConnection, "USE walmart;");
        phoneRepository.create(new Phone(31, "UpdateMePhone", "'space_grey'", "'sprint'", 7, 799.0));
        Phone newPhone = new Phone(31,"GrapeFruit5000","'gold'","'AT&T'",4,999.99 );
        //when
        phoneRepository.update(31,newPhone);
        //then
        Phone retrievedPhone = phoneRepository.read(31);
        Assert.assertEquals(newPhone,retrievedPhone);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testDelete_byId(){
        //given
        Engine engine = new Engine();
        engine.registerJDBCDriver();
        Connection mysqlDbConnection = engine.getConnection("mysql");
        PhoneRepository phoneRepository = new PhoneRepository(mysqlDbConnection);
        engine.executeStatement(mysqlDbConnection, "USE walmart;");
        phoneRepository.create(new Phone(55, "yPhone55", "'space_grey'", "'sprint'", 7, 799.0));
        //when
        phoneRepository.delete(55);
        Phone deleted = phoneRepository.read(55);
        //then

    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testDelete_byPhone(){
        //given
        Engine engine = new Engine();
        engine.registerJDBCDriver();
        Connection mysqlDbConnection = engine.getConnection("mysql");
        PhoneRepository phoneRepository = new PhoneRepository(mysqlDbConnection);
        engine.executeStatement(mysqlDbConnection, "USE walmart;");
        Phone myPhone = new Phone(55, "yPhone55", "'space_grey'", "'sprint'", 7, 799.0);
        phoneRepository.create(myPhone);
        //when
        phoneRepository.delete(myPhone);
        Phone deleted = phoneRepository.read(55);
        //then

    }



}
