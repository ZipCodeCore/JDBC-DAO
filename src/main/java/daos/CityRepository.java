package daos;

import models.City;

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
public class CityRepository implements Repo {
    private Connection connection;

    public CityRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public void create(City city) {
        executeStatement(String.format(new StringBuilder()
                        .append("INSERT INTO ciudades.city(")
                        .append("id, name, population, level) ")
                        .append("VALUES (%s, '%s', %s, %s);")
                        .toString(),
                city.getId(),
                city.getName(),
                city.getPopulation(),
                city.getLevel()));
    }

    public List<City> readAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM ciudades.city;");
        List<City> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String population = resultSet.getString(3);
                String level = resultSet.getString(4);
                list.add(new City(
                        Long.parseLong(id),
                        name,
                        Integer.parseInt(population),
                        Integer.parseInt(level)));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return list;
    }

    public City read(Long cityId) {
        return readAll()
                .stream()
                .filter(City -> City.getId().equals(cityId))
                .findAny()
                .get();
    }

    public void update(Long id, City newCityData) {
    executeStatement(String.format(new StringBuilder()
            .append("UPDATE ciudades.city SET ")
            .append("name = '%s', ")
            .append("population = '%s',")
            .append("level = '%s' ")
            .append("WHERE id = %s;")
            .toString(),

            newCityData.getName(),
            newCityData.getPopulation(),
            newCityData.getLevel(),
            id));


    }

    public void delete(Long id) {
        executeStatement(String.format(new StringBuilder()
                 .append("DROP FROM ciudades.city ")
                .append("WHERE id = %s;")
                .toString(),
                id));

    }

    public void delete(City city) {
    delete(city.getId());

    }

}
