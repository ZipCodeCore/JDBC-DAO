package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author git-leon
 * @version 1.0.0
 * @date 8/4/21 3:03 PM
 */
public class Pokemon {
    private Long id;
    private String name;
    private int primaryType;
    private int secondaryType;

    public Pokemon() {
    }

    public Pokemon(Long id, String name, int primaryType, int secondaryType) {
        this.id = id;
        this.name = name;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
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

    public int getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(int primaryType) {
        this.primaryType = primaryType;
    }

    public int getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(int secondaryType) {
        this.secondaryType = secondaryType;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Pokemon{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", primaryType=" + primaryType +
                    ", secondaryType=" + secondaryType +
                    '}';
        }
    }
}
