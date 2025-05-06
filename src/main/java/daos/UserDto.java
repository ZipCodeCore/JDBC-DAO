package daos;

import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDto implements DTO<User> {
    public Integer getId(User user) {
        return user.getId();
    }
    public static Integer getLastIdOfLast() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM Users ORDER BY id DESC LIMIT 1;");
            if (resultSet.next()) return resultSet.getInt("id");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
