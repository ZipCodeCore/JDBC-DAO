package daos;

import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DAOTest {

    DAO testDao;


    @Before
    public void setUp() throws Exception {
        testDao = new DAO();
    }


    @Test
    public void findById() {
        User expected = testDao.findById(4);
        Assert.assertEquals("Nedda",expected.getName());
    }

    @Test
    public void findAll() {
        List<User> allUsers = testDao.findAll();
        Integer expected = 10;
        Integer actual = allUsers.size();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void updateUser() {
        String expected = "newpassword";
        User testUpdate = new User(7,"Matilda",expected,null,null);
        testDao.updateUser(testUpdate);

        User updated = testDao.findById(7);
        String actual = updated.getPass();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void createUser() {
        User testUpdate = new User(19,"Mark",null,null,null);
        testDao.createUser(testUpdate);
        Integer expected = 11;
        Integer actual = testDao.findAll().size();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void deleteUser() {
        testDao.deleteUser(7);
        Integer expected = 10;
        Integer actual = testDao.findAll().size();

        Assert.assertEquals(expected,actual);
    }
}