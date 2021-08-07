package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author git-leon
 * @version 1.0.0
 * @date 8/4/21 3:03 PM
 */
public class Vehicle {
    private Long id;
    private String make;
    private String model;
    private Integer year;
    private String color;
    private String vin;

    public Vehicle() {
    }

    public Vehicle(Long id, String make, String model, Integer year, String color, String vin) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.vin = vin;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "Vehicle{" +
                    "id=" + id +
                    ", make='" + make + '\'' +
                    ", model=" + model +
                    ", year=" + year +
                    ", color=" + color +
                    ", vin=" + vin +
                    '}';
        }

    }
}
