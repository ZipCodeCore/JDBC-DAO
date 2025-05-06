package daos;

import models.Car;
import models.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Car_Dto implements Dto {
    Connection connection = ConnectionFactory.getConnection();

    public int getID(Car car) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Id FROM car WHERE Id=" + car.getId());
            if(rs.next())
            {
                Car cars = new Car();
                cars.setId( rs.getInt(1) );
                cars.setMake( rs.getString("Nissan") );
                cars.setModel( rs.getString("Maxima") );
                cars.setColor( rs.getString("White") );
                cars.setYear(rs.getInt(2017));
                return cars.getId();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return -1;
    }
}
