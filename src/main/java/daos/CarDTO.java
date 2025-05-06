package daos;

public class CarDTO implements DTO {
    Integer carId;
    String make;
    String model;
    Integer year;
    String color;
    String vin;

    public CarDTO() {
    }

    public CarDTO(String make, String model, Integer year, String color, String vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.vin = vin;
    }

    public CarDTO(Integer carId, String make, String model, Integer year, String color, String vin) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.vin = vin;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
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

    public Integer getId() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("Car ID: %d, Make: %s, Model: %s, Year: %d, Color: %s, VIN: %s", carId, make, model, year, color, vin);
    }
}
