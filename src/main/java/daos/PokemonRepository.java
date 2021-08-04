package daos;

import com.sun.deploy.net.MessageHeader;
import models.Pokemon;

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
public class PokemonRepository implements Repo {
    private Connection connection;

    public PokemonRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public void create(Pokemon pokemon) {
        executeStatement(String.format(new StringBuilder()
                        .append("INSERT INTO databaseName.pokemonTable(")
                        .append("id, name, primary_type, secondary_type) ")
                        .append("VALUES (%s, '%s', %s, %s);")
                        .toString(),
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getPrimaryType(),
                pokemon.getSecondaryType()));
    }

    public List<Pokemon> readAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM databaseName.pokemonTable;");
        List<Pokemon> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String primaryType = resultSet.getString(3);
                String secondaryType = resultSet.getString(4);
                list.add(new Pokemon(
                        Long.parseLong(id),
                        name,
                        Integer.parseInt(primaryType),
                        Integer.parseInt(secondaryType)));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return list;
    }

    public Pokemon read(Long pokemonId) {
        return readAll()
                .stream()
                .filter(pokemon -> pokemon.getId().equals(pokemonId))
                .findAny()
                .get();
    }

    public void update(Long id, Pokemon newPokemonData) {

    }

    public void delete(Long id) {

    }

    public void delete(Pokemon pokemon) {

    }

}
