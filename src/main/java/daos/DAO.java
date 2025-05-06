package daos;

import models.User;
import models.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO implements UserDAO {

    private List<User> allUsers;

    public User findById(Integer id) {

        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id=" + id);
            if(rs.next())
            {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<User> findAll() {
        allUsers = new ArrayList<User>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            while(rs.next())
            {
                allUsers.add(extractUserFromResultSet(rs));
            }
            return allUsers;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Boolean updateUser(User dto) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = createStatement(StatementType.UPDATE,connection,dto);
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Boolean createUser(User dto) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = createStatement(StatementType.INSERT,connection,dto);
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    public Boolean deleteUser(Integer id) {

        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM user WHERE id=" + id);
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId( rs.getInt("id") );
        user.setName( rs.getString("name") );
        user.setPass( rs.getString("password") );
        user.setEmail(rs.getString("email") );
        user.setPhoneNumber(rs.getString("phone_number"));
        return user;
    }

    private PreparedStatement createStatement (StatementType stmtype, Connection connection, User dto) throws SQLException {
        PreparedStatement ps;

        if (stmtype.getStatementType().equals("Insert")){
            ps = connection.prepareStatement("INSERT INTO user VALUES (NULL, ?, ?, ?,?)");
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getPass());
            ps.setString(3,dto.getEmail());
            ps.setString(4,dto.getPhoneNumber());

            return ps;
        }

        if (stmtype.getStatementType().equals("Update")){
            ps = connection.prepareStatement("UPDATE user SET name=?, password=?, email=?, phone_number=? WHERE id=?");
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getPass());
            ps.setString(3,dto.getEmail());
            ps.setString(4,dto.getPhoneNumber());
            ps.setInt(5, dto.getId());

            return ps;
        }
        return null;
    }
}

enum StatementType{
    INSERT (1,"Insert"), UPDATE (2,"Update");

    private String statementType;


    private Integer key;

    StatementType(Integer key, String stmtType) {
        statementType = stmtType;
        this.key = key;
    }


    public String getStatementType() {
        return statementType;
    }

    public Integer getKey() {
        return key;
    }

}