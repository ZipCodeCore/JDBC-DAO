package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOConcrete implements DAO <CarDTO> {      //This class does CRUD operations

    public CarDTO findById(Integer id) {
        Connection connection = ConnectToDB.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from car where carid=" + id);
            if (rs.next()){
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List findAll() {
        Connection connection = ConnectToDB.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from car");
            List carsList = new ArrayList();

            while (rs.next()){

                CarDTO car = extractUserFromResultSet(rs);
                carsList.add(car);
            }
            return carsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String update(CarDTO dto) {
        Connection connection = ConnectToDB.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("update car set  make=?, model=?, yearbuilt=?, color =?, vin=? where carid=?");

            ps.setString(1, dto.getMake());
            ps.setString(2, dto.getModel());
            ps.setInt(3, dto.getYear());
            ps.setString(4, dto.getColor());
            ps.setString(5, dto.getVin());
            ps.setInt(6, dto.getCarId());
            int i = ps.executeUpdate();

            if (i == 1) {
                return dto.toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String create (CarDTO dto) {
        Connection connection = ConnectToDB.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("Insert into car values(?, ?, ?, ?, ?, ?)");
            ps.setInt(1, dto.getCarId());
            ps.setString(2, dto.getMake());
            ps.setString(3, dto.getModel());
            ps.setInt(4, dto.getYear());
            ps.setString(5, dto.getColor());
            ps.setString(6, dto.getVin());
            int i = ps.executeUpdate();

            if (i == 1) {
                return dto.toString();
                //return dto;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Boolean delete(Integer id) {
        Connection connection = ConnectToDB.getConnection();

        try{
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate(("delete from car where carid=" + id));

            if(i >= 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private CarDTO extractUserFromResultSet (ResultSet rs) throws SQLException {
        CarDTO myCar = new CarDTO();

        myCar.setCarId(rs.getInt("carid"));
        myCar.setMake((rs.getString("make")));
        myCar.setModel((rs.getString("model")));
        myCar.setYear(rs.getInt("yearbuilt"));
        myCar.setColor((rs.getString("color")));
        myCar.setVin((rs.getString("vin")));

        return myCar;
    }
}
