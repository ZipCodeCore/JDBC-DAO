package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Phone {

    private Integer id;
    private String name;
    private String color;
    private String carrier;
    private Integer cameras;
    private Double price;

    public Phone() {
    }

    public Phone(Integer id, String name, String color, String carrier, Integer cameras, Double price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.carrier = carrier;
        this.cameras = cameras;
        this.price = price;
    }


    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Integer getCameras() {
        return cameras;
    }

    public void setCameras(Integer cameras) {
        this.cameras = cameras;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Phone{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", color=" + color +
                    ", carrier=" + carrier +
                    ", cameras=" + cameras +
                    ", price=" + price +
                    '}';
        }
    }

}
