package models;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;


public class ConnectionFactory {

    public static final String URL = "jdbc:mysql://localhost:3306/car";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static Connection getConnection(){
        try {
            DriverManager.registerDriver(new Driver() {
                @Override
                public Connection connect(String url, Properties info) throws SQLException {
                    return null;
                }

                @Override
                public boolean acceptsURL(String url) throws SQLException {
                    return false;
                }

                @Override
                public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
                    return new DriverPropertyInfo[0];
                }

                @Override
                public int getMajorVersion() {
                    return 0;
                }

                @Override
                public int getMinorVersion() {
                    return 0;
                }

                @Override
                public boolean jdbcCompliant() {
                    return false;
                }

                @Override
                public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                    return null;
                }
            });
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException ex){
            throw new RuntimeException("Error connecting to database", ex);
        }
    }

    public static void main(String[] args) {
        ConnectionFactory.getConnection();
        System.out.println("Connection Successful");
    }
}
