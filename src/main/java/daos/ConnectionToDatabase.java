package daos;

//import com.mysql.jdbc.Driver;
//import com.sun.jdi.connect.Connector;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class ConnectionToDatabase {

    private static final String URL = "jdbc:mysql://localhost:5432/cars";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static ConnectionToDatabase connectionToDatabase = new ConnectionToDatabase();



    public static Connection getConnection() {
        try {
            // DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }


    public static void main(String[] args) {
        Connection connection = connectionToDatabase.getConnection();
    }


    public static void javaToPostgres() {
        String query = "select count(*) from cars";

        try {
            con = DriverManager.getConnection(URL, USER, PASS);

            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of cars in the table : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {

            try {
                con.close();
            } catch (SQLException se) {
            }
            try {
                stmt.close();
            } catch (SQLException se) {
            }
            try {
                rs.close();
            } catch (SQLException se) {
            }
        }
    }

    private Car extractUserFromResultSet(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setMake(rs.getString("make"));
        car.setModel(rs.getString("model"));
        car.setYear(rs.getInt("year"));
        car.setColor(rs.getString("color"));
        car.setVin(rs.getString("vin"));
        return car;
    }

    public Car getCar(int id) {
        Connection connection = connectionToDatabase.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id=" + id);
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Car getUserByUserNameAndPassword(String user, String pass) {
        Connection connection = connectionToDatabase.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE user=? AND pass=?");
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Set getAllUsers() {
        Connection connection = connectionToDatabase.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            Set cars = new HashSet();
            while (rs.next()) {
                Car car = extractUserFromResultSet(rs);
                cars.add(car);
            }
            return cars;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public boolean insertUser(Car car) {
        Connection connection = connectionToDatabase.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user VALUES (NULL, ?, ?, ?,?,?)");
            ps.setString(1, car.getMake());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYear());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getVin());

            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(Car car) {
        Connection connection = connectionToDatabase.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE user SET make=?, model=?, year=?,color=?, vin=? WHERE id=?");

            ps.setString(1, car.getMake());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYear());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getVin());
            ps.setInt(6, car.getId());

            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int id) {
        Connection connection = connectionToDatabase.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM user WHERE id=" + id);
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


}





