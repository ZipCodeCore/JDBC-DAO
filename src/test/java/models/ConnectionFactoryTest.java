package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionFactoryTest {
    Connection connection;


    @Test
    public void getConnection() {
       connection = ConnectionFactory.getConnection();
        Boolean actual = false;

        try {
            actual = connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertFalse(actual);
    }
}