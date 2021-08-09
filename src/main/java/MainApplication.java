
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
        executeStatement(mysqlDbConnection, "DROP DATABASE IF EXISTS Vehicles;");
        executeStatement(mysqlDbConnection, "CREATE DATABASE IF NOT EXISTS Vehicles;");
        executeStatement(mysqlDbConnection, "USE Vehicles;");
        executeStatement(mysqlDbConnection, new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS Vehicles.cars(")
                .append("id int auto_increment primary key,")
                .append("make text not null,")
                .append("year int not null,")
                .append("vin int not null,")
                .append("color text null);")
                .toString());

        carsRepository.create(new Cars(1L, "Kia", 2016, 1891L, "red" ));
        carsRepository.create(new Cars(2L, "Ford", 2004, 5678L, "purple" ));
        carsRepository.create(new Cars(3L, "Toyota", 1984, 4685L, "black" ));
        carsRepository.create(new Cars(4L, "Tesla", 2020, 1627L, "black" ));
        carsRepository.create(new Cars(5L, "Nissan", 2010, 1467L, "silver" ));

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
        String username = "root";
        String password = "zipcode0";
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
