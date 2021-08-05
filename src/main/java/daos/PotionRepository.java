package daos;

import models.Potion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PotionRepository implements Repository{

    private Connection connection;

    public PotionRepository(Connection connection) {
        this.connection = connection;
    }
    public Connection getConnection() {
        return connection;
    }

    public void create(Potion potion) {
        executeStatement(String.format(new StringBuilder()
                .append("INSERT INTO potions.potionsTable(")
                .append("id, name, ingredient1, ingredient2, effect)")
                .append("VALUES (%s, '%s', '%s', '%s', '%s');")
                .toString(),
            potion.getId(),
            potion.getName(),
            potion.getIngredient1(),
            potion.getIngredient2(),
            potion.getEffect()));
    }

    public List<Potion> findAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM potions.potionsTable;");
        List<Potion> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String ingredient1 = resultSet.getString(3);
                String ingredient2 = resultSet.getString(4);
                String effect = resultSet.getString(5);
                list.add(new Potion(
                        Long.parseLong(id),
                        name,
                        ingredient1,
                        ingredient2,
                        effect));

            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return list;
    }

    public Potion findById(Long id) {
        return findAll()
                .stream()
                .filter(potion -> potion.getId().equals(id))
                .findAny()
                .get();
    }

    public void update(Long id, Potion newPotionData) {
        executeStatement(String.format(new StringBuilder()
                .append("UPDATE potions.potionsTable ")
                .append("SET name = '%s', ingredient1 = '%s', ingredient2 = '%s', effect = '%s' WHERE id = %s;")
                .toString(),
            newPotionData.getName(),
            newPotionData.getIngredient1(),
            newPotionData.getIngredient2(),
            newPotionData.getEffect(),
            id));
    }

    public void delete(Long id) {
        Potion potion = findById(id);
        executeStatement(String.format(new StringBuilder()
            .append("DELETE FROM potions.potionsTable WHERE id = %s")
            .toString(),
            id));
        System.out.printf("%s has been deleted", potion.toString());
    }

    public void delete(Potion potion) {
        Long id = potion.getId();
        delete(id);
    }
}
