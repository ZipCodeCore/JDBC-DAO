package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Potion {

    private Long id;
    private String name;
    private String ingredient1;
    private String ingredient2;
    private String effect;

    public Potion() {}

    public Potion(Long id, String name, String ingredient1, String ingredient2, String effect) {
        this.id = id;
        this.name = name;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.effect = effect;
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

    public String getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "Potion {" +
                "ID = " + id +
                ", Name = " + name +
                ", First Ingredient = " + ingredient1 +
                ", Second Ingredient = " + ingredient2 +
                ", Effects = " + effect +
                "}";
    }
}
