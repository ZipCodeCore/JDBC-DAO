package daos;

import models.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.MissingFormatArgumentException;

public class Carepository implements Carepo {
    private Connection connection;

    public Carepository(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Connection getConnection() {
        return connection;
    }

    public void create(Car car){
        try {
            String query = String.format(new StringBuilder()
                            .append("INSERT INTO showroom.car(")
                            .append("make, model,year,color,VIN) ")
                            .append("VALUES ('%s','%s',%s,'%s',%s);")
                            .toString(),
                    //car.getId(),
                    car.getMake(),
                    car.getModel(),
                    car.getYear(),
                    car.getColor(),
                    car.getVIN());
            System.out.println("query" + query);
            executeStatement(query);
        }
        catch (MissingFormatArgumentException m) {
            System.out.println(m.getMessage());
        }

    }

    public Car read(Long carId) {
        return readAll()
                .stream()
                .filter(car->car.getId()==carId)
                .findAny()
                .get();
    }

    public List<Car> readAll(){
        ResultSet resultSet = executeQuery(String.format("SELECT * FROM showroom.car;"));
        List<Car> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                String year = resultSet.getString(4);
                String color = resultSet.getString(5);
                String VIN = resultSet.getString(6);

                list.add(new Car(
                        make, model, Integer.parseInt(year), color, Integer.parseInt(VIN)));
            }
    } catch (SQLException throwables) {
        throw new RuntimeException(throwables);
    }
      return list;
    }

    public void update(Long id,Car car){

    }

    public void delete(Long id){

    }

    public void delete(Car car){}


}
