package daos;

import org.junit.Test;

import static org.junit.Assert.fail;

public class TestConnectionFactory {
    @Test
    public void testGetConnection() {
        try {
            ConnectionFactory.getConnection();
        } catch( RuntimeException rte) {
            fail("Could not connect to MySQL database");
        }

    }
}
