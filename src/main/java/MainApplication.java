
import com.mysql.cj.jdbc.Driver;
import daos.CarRepo;
import models.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author git-leon
 * @version 1.0.0
 * @date 8/2/21 9:49 AM
 */
public class MainApplication {

    public static void main(String[] args) {
        registerJDBCDriver();
        Connection mysqlDbConnection = getConnection("mysql");
        CarRepo<Car> carRepo = new CarRepo<>(mysqlDbConnection);
        executeStatement(mysqlDbConnection, "DROP DATABASE IF EXISTS zcwdblabs;");
        executeStatement(mysqlDbConnection, "CREATE DATABASE IF NOT EXISTS zcwdblabs;");
        executeStatement(mysqlDbConnection, "USE zcwdblabs;");
        executeStatement(mysqlDbConnection, new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS car(")
                .append("id int auto_increment primary key,")
                .append("make varchar(255) not null,")
                .append("model varchar(255) not null,")
                .append("year int not null,")
                .append("color varchar(255),")
                .append("vin int not null);")
                .toString());

        carRepo.create(new Car(4, "Ford", "Ranger", 1999, "White", 491883));
        carRepo.create(new Car(14, "jeep", "wrangler", 2017, "rhinogrey", 52513));
        carRepo.update(14, new Car("Klondike", "Bar", 2018, "wigglenuts", 1451235));
        System.out.println(carRepo.findAll());

//        executeStatement(mysqlDbConnection, new StringBuilder()
//                .append("INSERT INTO zcwdblabs.car(")
//                .append("id, name, primary_type, secondary_type) ")
//                .append("VALUES (12, 'Ivysaur', 3, 7);")
//                .toString());
//
//        executeStatement(mysqlDbConnection, new StringBuilder()
//                .append("INSERT INTO zcwdblabs.car(")
//                .append("id, name, primary_type, secondary_type) ")
//                .append("VALUES (13, 'Ivysaurr', 3, 7);")
//                .toString());

//        String getPokemonTable = "SELECT * FROM zcwdblabs.car;";
//        ResultSet resultSet = executeQuery(mysqlDbConnection, getPokemonTable);
//        printResults(resultSet);
//
//        String showPokemonTable = "SHOW DATABASES;";
//        resultSet = executeQuery(mysqlDbConnection, showPokemonTable);
//        printResults(resultSet);
    }

    static ResultSet executeQuery(Connection connection, String sqlQuery) {
        try {
            Statement statement = getScrollableStatement(connection);
            return statement.executeQuery(sqlQuery);
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
        String username = "nick";
        String password = "password";
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