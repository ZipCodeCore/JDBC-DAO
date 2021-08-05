package models;

public class Car {
    private long id;
    private String make;
    private String model;
    private Integer year;
    private String color;
    private Integer VIN;


    public Car() {
    }

    public Car( String make, String model, Integer year, String color, Integer VIN) {
        //this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.VIN = VIN;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Integer getVIN() {
        return VIN;
    }

    public void setVIN(Integer VIN) {
        this.VIN = VIN;
    }

}
