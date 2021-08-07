import daos.StudentRepository;
import models.Student;

import com.mysql.jdbc.Driver;
import java.sql.*;
import java.time.LocalDate;
import java.util.StringJoiner;

public class MainApplication {

    public static void main(String[] args) {
        registerJDBCDriver();
        Connection mysqlDbConnection = getConnection("mysql");
        StudentRepository studentRepository = new StudentRepository(mysqlDbConnection);
        executeStatement(mysqlDbConnection, "DROP DATABASE IF EXISTS IAODataTest;");
        executeStatement(mysqlDbConnection, "CREATE DATABASE IF NOT EXISTS IAODataTest;");
        executeStatement(mysqlDbConnection, "USE IAODataTest;");
        executeStatement(mysqlDbConnection, new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS IAODataTest.students(")
                .append("id int auto_increment primary key,")
                .append("name text not null,")
                .append("grade int not null,")
                .append("school text,")
                .append("age int);")
                .toString());

        studentRepository.create(new Student(10L, "Thai", 3, "Sanford", LocalDate.of(2011, 11, 11)));
        //pokemonRepository.create(new Pokemon(11L, "Ivysaurr", 3, 7));
        System.out.println(studentRepository.readAll());

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


    //change root name and password to local info
    static Connection getConnection(String dbVendor) {
        String username = "root";
        String password = "pw";
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
