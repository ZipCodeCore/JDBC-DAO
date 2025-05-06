package models;

import com.mysql.cj.jdbc.Driver;
import daos.CityRepository;
import daos.Conecter;

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
        Conecter conecter = new Conecter();
        conecter.registerJDBCDriver();
        Connection mysqlDbConnection = conecter.getConnection("mysql");
        CityRepository cityRepository = new CityRepository(mysqlDbConnection);
        executeStatement(mysqlDbConnection, "DROP DATABASE IF EXISTS ciudades;");
        executeStatement(mysqlDbConnection, "CREATE DATABASE IF NOT EXISTS ciudades;");
        executeStatement(mysqlDbConnection, "USE ciudades;");
        executeStatement(mysqlDbConnection, new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS ciudades.city(")
                .append("id int auto_increment primary key,")
                .append("name text not null,")
                .append("population int not null,")
                .append("level int null);")
                .toString());

       cityRepository.create(new City(12L, "Chicago", 3, 7));
       cityRepository.create(new City(13L, "New York", 8, 4));
        System.out.println(cityRepository.readAll());

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
                String forthColumnData = resultSet.getString(4);
                System.out.println(new StringJoiner("\n")
                        .add("Row number = " + rowNumber)
                        .add("First Column = " + firstColumnData)
                        .add("Second Column = " + secondColumnData)
                        .add("Third column = " + thirdColumnData)
                        .add("Forth Column = " + forthColumnData));
            }
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    static void executeStatement(Connection connection, String sqlStatement) {
        try {
            Statement statement = getScrollableStatement(connection);
            statement.execute(sqlStatement);
           // connection.setAutoCommit(false);
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

//    static Connection getConnection(String dbVendor) {
//        String username = "laura";
//        String password = "zipcode0";
//        String url = new StringBuilder()
//                .append("jdbc:")
//                .append(dbVendor)
//                .append("://127.0.0.1/")
//                .append("?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
//                .toString();
//        try {
//            return DriverManager.getConnection(url, username, password);
//        } catch (SQLException e) {
//            throw new Error(e);
//        }
//    }
//
//    static void registerJDBCDriver() {
//        // Attempt to register JDBC Driver
//        try {
//            DriverManager.registerDriver(Driver.class.newInstance());
//        } catch (InstantiationException | IllegalAccessException | SQLException e1) {
//            throw new RuntimeException(e1);
//        }
//    }

}
