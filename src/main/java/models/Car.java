package models;

public class Car {
    private Integer id;
    private String make;
    private String model;
    private String color;
    private Integer year;

    public Car(){
    }

    public Car(String make, String model, String color, Integer year){
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public Car(Integer id, String make, String model, String color, Integer year){
        this.id = id;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Integer getYear() {
        return year;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setYear(Integer year) {
        this.year = year;
    }




}
