package models;

import java.util.Date;

public class Pet implements PetDto {

    private Integer petId;
    private String petName;
    private String petOwner;
    private String petType;
    private String petSex;

    public Pet(){
    }

    public Pet(Integer petId, String petName, String petOwner, String petType,
               String petSex){

        this.petId = petId;
        this.petName = petName;
        this.petOwner = petOwner;
        this.petType = petType;
        this.petSex = petSex;
    }

    public Pet(String petName, String petOwner, String petType,
               String petSex){

        this.petName = petName;
        this.petOwner = petOwner;
        this.petType = petType;
        this.petSex = petSex;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(String petOwner) {
        this.petOwner = petOwner;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetSex() {
        return petSex;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }



}
