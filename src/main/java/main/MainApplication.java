package main;

import daos.PotionRepository;
import models.Potion;

import java.sql.Connection;
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
        SQLConnect.registerJDBCDriver();
        Connection mysqlDbConnection = SQLConnect.getConnection("mysql");
        PotionRepository pokemonRepository = new PotionRepository(mysqlDbConnection);
        executeStatement(mysqlDbConnection, "DROP DATABASE IF EXISTS potions;");
        executeStatement(mysqlDbConnection, "CREATE DATABASE IF NOT EXISTS potions;");
        executeStatement(mysqlDbConnection, "USE potions;");
        executeStatement(mysqlDbConnection, new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS potions.potionsTable(")
                .append("id int auto_increment primary key,")
                .append("name text not null,")
                .append("ingredient1 text not null,")
                .append("ingredient2 text not null,")
                .append("effect text not null);")
                .toString());

        pokemonRepository.create(new Potion(1L, "Restore Health", "blue mountain flower", "daedra heart", "health regen"));
        pokemonRepository.create(new Potion(2L, "Restore Magicka", "briar heart", "red mountain flower", "magicka regen"));
        System.out.println(pokemonRepository.findAll());

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
}