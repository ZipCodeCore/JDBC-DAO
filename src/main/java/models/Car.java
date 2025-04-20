package models;

public class Car implements BaseModel {

    int id;
    String make;
    String model;
    String color;
    int year;
    long vin;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getVin() {
        return vin;
    }

    public void setVin(long vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return String.format("Car {id: %s, make: %s, model: %s, year: %s, color: %s, vin: %s}", id, make, model, year, color, vin);
    }
}
