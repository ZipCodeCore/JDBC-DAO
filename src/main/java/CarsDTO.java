
public class CarsDTO implements DTO {      //<------this is Data transfer object class
  private Integer ID;
  private String MAKE;
  private String MODEL;
  private String Year;
  private String COLOR;
  private String VIN;

    public CarsDTO(Integer ID, String MAKE, String MODEL, String year, String COLOR, String VIN) {
        this.ID = ID;
        this.MAKE = MAKE;
        this.MODEL = MODEL;
        Year = year;
        this.COLOR = COLOR;
        this.VIN = VIN;
    }
    /*
    public CarsDTO(String MAKE, String MODEL, String year, String COLOR, String VIN){
        this.MAKE = MAKE;
        this.MODEL = MODEL;
        Year = year;
        this.COLOR = COLOR;
        this.VIN = VIN;
    }

     */

    public CarsDTO(){
    }

   public Integer getID() {
       return ID;
    }

    public String getMAKE() {
        return MAKE;
    }

    public String getMODEL() {
        return MODEL;
    }

    public String getYear() {
        return Year;
    }

    public String getCOLOR() {
        return COLOR;
    }

    public String getVIN() {
        return VIN;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setMAKE(String MAKE) {
        this.MAKE = MAKE;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }

    public void setYear(String year) {
        Year = year;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public int getId() {
        return ID;
    }

    public String toString(){
        return String.format("Car={car id = %d, make = %s, model = %s, year = %s, color = %s, vin = %s}",getID(),getMAKE(),getMODEL(),getYear(),getCOLOR(),getVIN());
    }
}
