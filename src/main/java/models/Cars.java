package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author git-leon
 * @version 1.0.0
 * @date 8/4/21 3:03 PM
 */
public class Cars {
    private Long id;
    private String make;
    private Integer year;
    private  Integer MPG;
    private String color;





    public void setId(Long id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }


    public void setYear(Integer year) {
        this.year = year;
    }

    public void setMPG(Integer MPG) {
        this.MPG = MPG;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public Object getColor() {
        return  color;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMPG() {
        return MPG;
    }

    public Cars(Long id, String make,  Integer year, String color, Integer MPG) {
        this.id = id;
        this.make = make;
        this.year = year;
        this.MPG = MPG;
        this.color = color;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Cars{" +
                    "id=" + id +
                    ", make='" + make + '\'' +
                    ", year='" + year +
                    ", color='" + color +
                    ", MPG=" + MPG +
                    '}';
        }
    }




}
