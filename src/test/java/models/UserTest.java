package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class UserTest {

    User testUser;

    @Before
    public void setUp() throws Exception {
        testUser = new User(1,"Joe","testpass","joe@gmail.com","215-222-3333");
    }

    @Test
    public void getId() {
        Integer actual = 1;
        Integer expected = testUser.getId();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setId() {
        Integer actual = 6;
        testUser.setId(actual);
        Integer expected = testUser.getId();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getName() {
        String expected = "Joe";
        String actual = testUser.getName();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setName() {
        String actual = "Jane";
        testUser.setName(actual);
        String expected = testUser.getName();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getPass() {
        String expected = "testpass";
        String actual = testUser.getPass();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setPass() {
        String expected = "secondpass";
        testUser.setPass(expected);
        String actual = testUser.getPass();

    }

    @Test
    public void getEmail() {
        String expected = "joe@gmail.com";
        String actual = testUser.getEmail();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setEmail() {
        String expected = "jane@gmail.com";
        testUser.setEmail(expected);
        String actual = testUser.getEmail();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getPhoneNumber() {
        String expected = "215-222-3333";
        String actual = testUser.getPhoneNumber();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setPhoneNumber() {
        String expected = "215-333-4444";
        testUser.setPhoneNumber(expected);
        String actual = testUser.getPhoneNumber();

        Assert.assertEquals(expected,actual);
    }
}