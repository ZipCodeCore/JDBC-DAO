package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author git-leon
 * @version 1.0.0
 * @date 8/4/21 3:03 PM
 */
public class Car {
    private Long id;
    private String make;
    private String model;
    private String color;
    private String vType;
    private int mpg;

    public Car() {
    }

    public Car(String make, String model, String color, String vType, int mpg) {
        this.id = null;
        this.make = make;
        this.model = model;
        this.color = color;
        this.vType = vType;
        this.mpg = mpg;
    }

    public Car(Long id, String make, String model, String color, String vType, int mpg) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.color = color;
        this.vType = vType;
        this.mpg = mpg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }

    public int getMpg() {
        return mpg;
    }

    public void setMpg(int mpg) {
        this.mpg = mpg;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Pokemon{" +
                    "id=" + id +
                    ", Make='" + make + '\'' +
                    ", Model=" + model +
                    ", Color=" + color +
                    ", VehicleType= " + vType +
                    ", Mpg= " + mpg +
                    '}';
        }
    }
}