package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author git-leon
 * @version 1.0.0
 * @date 8/4/21 3:03 PM
 */
public class City {
    private Long id;
    private String name;
    private int population;
    private int level;

    public City() {
    }

    public City(Long id, String name, int population, int level) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "City{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", population=" + population +
                    ", level=" + level +
                    '}';
        }
    }
}
