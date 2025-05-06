package daos;

import models.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDao implements DAO<User> {

    public User findById(Integer id) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            String query = "SELECT * FROM Users WHERE id = " + id + ";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) return extractUserFromResultSet(resultSet);

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Set<User> findAll() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users;");
            Set users = new HashSet();
            while(resultSet.next())
            {
                User user = extractUserFromResultSet(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Boolean insert(User user) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Users VALUES (NULL, ?, ?, ?, ?, ?);");
            ps = prepareUserForImport(ps, user);

            int i = ps.executeUpdate();
            if(i == 1) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean update(Integer id, User user) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Users SET first_name=?, last_name=?, fav_color=?, job=?, birthdate=? WHERE id=?;");
            ps = prepareUserForImport(ps, user);
            ps.setInt(6, id);

            int i = ps.executeUpdate();
            if(i == 1) return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean delete(Integer id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate("DELETE FROM Users WHERE id=" + id + ";");
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId( rs.getInt("id") );
        user.setFirstName( rs.getString("first_name") );
        user.setLastName( rs.getString("last_name") );
        user.setFavColor( rs.getString("fav_color") );
        user.setJob( rs.getString("job") );
        user.setBirthday( rs.getDate("birthdate") );
        return user;
    }

    private PreparedStatement prepareUserForImport(PreparedStatement ps, User user) throws SQLException {
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getFavColor());
        ps.setString(4, user.getJob());
        ps.setDate(5, new java.sql.Date(user.getBirthday().getTime()));
        return ps;
    }
}
