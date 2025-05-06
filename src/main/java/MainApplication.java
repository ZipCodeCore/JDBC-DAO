
import com.mysql.cj.jdbc.Driver;
import daos.CarsRepository;
import models.Cars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

/**
 * @author git-leon
 * @version 1.0.0
 * @date 8/2/21 9:49 AM
 */
public class MainApplication {

    public static void main(String[] args) {
        registerJDBCDriver();
        Connection mysqlDbConnection = getConnection("mysql");
        CarsRepository carsRepository = new CarsRepository(mysqlDbConnection);
        executeStatement(mysqlDbConnection, "DROP DATABASE IF EXISTS Objects;");
        executeStatement(mysqlDbConnection, "CREATE DATABASE IF NOT EXISTS Objects;");
        executeStatement(mysqlDbConnection, "USE Objects;");
        executeStatement(mysqlDbConnection, new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS Objects.cars(")
                .append("id int auto_increment primary key,")
                .append("make text not null,")
                .append("year int not null,")
                .append("color text not null,")
                .append("MPG int not null);")
                .toString());

        carsRepository.create(new Cars(1L, "Toyota", 1993, "red", 120 ));
        carsRepository.create(new Cars(2L, "Ford", 1980, "black", 100));
        carsRepository.create(new Cars(3L, "Jeep", 2000, "orange", 110));
        carsRepository.create(new Cars(4L, "Volks Wagon", 2005, "silver", 140));
        carsRepository.create(new Cars(5L, "Tesla", 2010, "silver", 150));
        System.out.println(carsRepository.readAll());

    }

    static ResultSet executeQuery(Connection connection, String sqlQuery) {
        try {
            Statement statement = getScrollableStatement(connection);
            return statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    static void printResults(ResultSet resultSet) {
        try {
            for (int rowNumber = 0; resultSet.next(); rowNumber++) {
                String firstColumnData = resultSet.getString(1);
                String secondColumnData = resultSet.getString(2);
                String thirdColumnData = resultSet.getString(3);
                System.out.println(new StringJoiner("\n")
                        .add("Row number = " + rowNumber)
                        .add("First Column = " + firstColumnData)
                        .add("Second Column = " + secondColumnData)
                        .add("Third column = " + thirdColumnData));
            }
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    static void executeStatement(Connection connection, String sqlStatement) {
        try {
            Statement statement = getScrollableStatement(connection);
            statement.execute(sqlStatement);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    static Statement getScrollableStatement(Connection connection) {
        int resultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try { // scrollable statements can be iterated more than once without closing
            return connection.createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    static Connection getConnection(String dbVendor) {
        String username = "song";
        String password = "songbass123";
        String url = new StringBuilder()
                .append("jdbc:")
                .append(dbVendor)
                .append("://127.0.0.1/")
                .append("?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
                .toString();
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    static void registerJDBCDriver() {
        // Attempt to register JDBC Driver
        try {
            DriverManager.registerDriver(Driver.class.newInstance());
        } catch (InstantiationException | IllegalAccessException | SQLException e1) {
            throw new RuntimeException(e1);
        }
    }

}
