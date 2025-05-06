package daos;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Connects to Database
public class ConnectionFactory {

    public static final String URL = Secrets.getURL();
    public static final String USER = Secrets.getUSER();
    public static final String PASS = Secrets.getPASS();

    //Get a connection to database
    public static Connection getConnection() {

        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    //test Connection
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
        MovieDAO mdao = new MovieDAO();
//        for (int i = 7; i < 14; i++) {
//            mdao.delete(i);
//        }
    }
}
