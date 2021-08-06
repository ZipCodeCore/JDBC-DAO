
import com.mysql.cj.jdbc.Driver;
import daos.VehicleRepository;
import models.Vehicle;

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
        VehicleRepository vehicleRepository = new VehicleRepository(mysqlDbConnection);
        executeStatement(mysqlDbConnection, "DROP DATABASE IF EXISTS model;");
        executeStatement(mysqlDbConnection, "CREATE DATABASE IF NOT EXISTS model;");
        executeStatement(mysqlDbConnection, "USE model;");
        executeStatement(mysqlDbConnection, new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS model.car(")
                .append("id int auto_increment primary key,")
                .append("make text not null,")
                .append("model text not null,")
                .append("year int null,")
                .append("color text not null,")
                .append("vin text not null);")
                .toString());

        vehicleRepository.create(new Vehicle(1L, "Tesla", "Model X", 2021, "Matte Black", "1FTFW1EF9DKE31717"));
        vehicleRepository.create(new Vehicle(2L, "Honda", "CR-V", 1999, "Silver", "1C3AN65L65X036242"));
        vehicleRepository.create(new Vehicle(3L, "Toyota", "Camry", 2010, "Gold", "4T1BE32K35U037372"));
        vehicleRepository.create(new Vehicle(4L, "Honda", "Accord", 1996, "Burgundy", "5UXWX9C56E0D36665"));
        vehicleRepository.create(new Vehicle(5L, "Nissan", "Altima", 2012, "Gray", "1G1AK15F177174588"));
        vehicleRepository.create(new Vehicle(6L, "Tesla", "Model S", 2020, "Red", "1FDKF37G0VEB13318"));
        System.out.println(vehicleRepository.readAll());



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
        String username = "Manny";
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
