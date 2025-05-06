package daos;

import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.Date;

public class TestUserDao {
    UserDao userDao = new UserDao();
    User user1 = new User("testy","test","testeroon", new Date(2000,12,25), "tester");
    User user2 = new User("Mr. Testy","Test","testeroon", new Date(1900,12,25), "tester");



    @Test
    public void testFindById() {
        String actual = userDao.findById(1).toString();
        String expected = "Wes Jones\t|\tblue\t|\tzipcoder\t|\t1997-08-05";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindAll() {
        Integer actual = userDao.findAll().size();
        Integer expected = 6;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testInsert() {
        Assert.assertTrue(userDao.insert(user1));
        Integer id = lastId();
        String actual = userDao.findById(id).toString();
        String expected = "testy test\t|\ttesteroon\t|\ttester\t|\t3901-01-25";
        Assert.assertEquals(expected, actual);
        userDao.delete(id);

    }

    @Test
    public void testUpdate() {
        Assert.assertTrue(userDao.insert(user1));
        Integer id = lastId();
        userDao.update(id, user2);
        String actual = userDao.findById(id).toString();
        String expected = "Mr. Testy Test\t|\ttesteroon\t|\ttester\t|\t3801-01-25";
        Assert.assertEquals(expected, actual);
        userDao.delete(id);
    }

    @Test
    public void testDelete() {
        Assert.assertTrue(userDao.insert(user1));
        Integer id = lastId();
        Assert.assertTrue(userDao.delete(id));
        Assert.assertEquals(6, userDao.findAll().size());
    }

    private Integer lastId() {
        return UserDto.getLastIdOfLast();
    }
}
