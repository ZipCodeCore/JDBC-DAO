package models;

import daos.Car_Dao;

import java.util.Set;

public class Application {

    public static void main(String[] args) {
        Set resultSet;
        Car_Dao car_dao = new Car_Dao();
        Car car1 = new Car(1, "Nissan", "Maxima", "White", 2017);
        Car car2 = new Car(2, "Subaru", "WRX STI", "White", 2018);
        Car car3 = new Car(3, "Chevy", "Corvette", "Orange", 2020);

        car_dao.create(car1);
        car_dao.create(car2);
        car_dao.create(car3);

        resultSet = car_dao.findAll();
        car_dao.update(car2);

        car_dao.delete(2018);

        System.out.println(car_dao.findByID(2018));

    }
}
