package daos;

import models.Phone;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author git-leon
 * @version 1.0.0
 * @date 8/4/21 3:05 PM
 */
public class PhoneRepository implements Repo {
    private Connection connection;

    public PhoneRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public void create(Phone phone) {
        executeStatement(String.format(new StringBuilder()
                        .append("INSERT INTO walmart.phone(")
                        .append("id, name, color, carrier, cameras, price)")
                        .append("VALUES (%s, '%s', %s, %s, %s, %s);")
                        .toString(),
                phone.getId(),
                phone.getName(),
                phone.getColor(),
                phone.getCarrier(),
                phone.getCameras(),
                phone.getPrice()));
    }

    public List<Phone> readAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM walmart.phone;");
        List<Phone> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String color = resultSet.getString(3);
                String carrier = resultSet.getString(4);
                Integer cameras = resultSet.getInt(5);
                Double price = resultSet.getDouble(6);
                list.add(new Phone(
                        Integer.parseInt(id),
                        name,
                        color,
                        carrier,
                        cameras,
                        price));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return list;
    }

    public Phone read(Integer phoneId) {
        return readAll()
                .stream()
                .filter(phone -> phone.getId() == phoneId)
                .findAny()
                .get();
    }

    public void update(Integer id, Phone newPhone) {
        executeStatement(new StringBuilder()
            .append("UPDATE phone SET ")
            .append("name = '" + newPhone.getName() + "'")
            .append(", color = " + newPhone.getColor())
            .append(", carrier = " + newPhone.getCarrier())
            .append(", cameras = " + newPhone.getCameras().toString())
            .append(", price = " + newPhone.getPrice().toString())
            .append("WHERE id =" + id.toString()).toString());
    }

    public void delete(Integer id) {
        executeStatement("DELETE FROM phone WHERE id = " + id.toString());
    }

    public void delete(Phone phone) {
        executeStatement("DELETE FROM phone WHERE id = " + phone.getId().toString());
    }



}