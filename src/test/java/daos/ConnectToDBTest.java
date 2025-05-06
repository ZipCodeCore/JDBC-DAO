package daos;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectToDBTest {


    @Test
    public void getConnection() throws SQLException {
        Connection connect = ConnectToDB.getConnection();
        Assert.assertFalse(connect.isClosed());
    }
}
