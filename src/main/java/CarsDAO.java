import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsDAO implements DAO<CarsDTO> {
    //ConnectionFactory cf = new ConnectionFactory();
    Connection connection = ConnectionFactory.getConnection();


    public CarsDTO findById(int id) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Cars WHERE id=" + id);
            if (rs.next()) {
                CarsDTO car = extractUserFromResultSet(rs);
                return car;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }


    private CarsDTO extractUserFromResultSet(ResultSet rs) throws SQLException {
        CarsDTO car = new CarsDTO();
        car.setID(rs.getInt("id"));
        car.setMAKE(rs.getString("make"));
        car.setMODEL(rs.getString("model"));
        car.setYear(rs.getString("year"));
        car.setCOLOR(rs.getString("color"));
        car.setVIN(rs.getString("vin"));
        return car;
    }


    public List findAll() {

        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Cars");
            List carList = new ArrayList();
            while (rs.next()) {
                CarsDTO car = extractUserFromResultSet(rs);
                carList.add(car);
            }
            return carList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public CarsDTO update(CarsDTO dto) {

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Cars SET MAKE=?, MODEL=?, YEAR=?, COLOR=?, VIN=? WHERE ID=?");
            ps.setString(1, dto.getMAKE());
            ps.setString(2, dto.getMODEL());
            ps.setString(3, dto.getYear());
            ps.setString(4, dto.getCOLOR());
            ps.setString(5, dto.getVIN());
            ps.setInt(6, dto.getID());
            int i = ps.executeUpdate();
            if (i == 1) {
                return dto;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public CarsDTO create(CarsDTO dto) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Cars VALUES (ID=?,  MAKE=?, MODEL=?, YEAR=?, COLOR=?, VIN=? ");
            ps.setInt(1, dto.getID());
            ps.setString(2, dto.getMAKE());
            ps.setString(3, dto.getMODEL());
            ps.setString(4, dto.getYear());
            ps.setString(5, dto.getCOLOR());
            ps.setString(6, dto.getVIN());
            int i = ps.executeUpdate();
            if (i == 1) {
                return dto;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void delete(int id) {
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM Cars WHERE id=" + id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
}
