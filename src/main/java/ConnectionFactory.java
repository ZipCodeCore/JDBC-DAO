import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;

public class ConnectionFactory {

    public static void main(String[] args) {
        getConnection();

    }

    public static final String URL = "jdbc:mysql://localhost:3306/zcwdblab?serverTimezone=UTC";
    public static final String USER = "nick";
    public static final String PASS = "password";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("ERROR : Can't connect to the database", e);
        }
    }







    static void registerJDBCDriver() {
        // Attempt to register JDBC Driver
        try {
            DriverManager.registerDriver(Driver.class.newInstance());
        } catch (InstantiationException | IllegalAccessException | SQLException e1) {
            throw new Error();
        }
    }

    static Statement getScrollableStatement(Connection connection) {
        int resultSetType =  ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try { // scrollable statements can be iterated more than once without closing
            return connection.createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    static void executeStatement(Connection connection, String sqlStatement) {
        try {
            Statement statement = getScrollableStatement(connection);
            statement.execute(sqlStatement);
            connection.commit();
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
}
