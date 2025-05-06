package models;

import daos.PetsConcreteDao;
import java.util.List;

public class AppRunner {


    public static void main(String[] args) {
        PetsConcreteDao pets = new PetsConcreteDao();

        printPet(pets.findById(2));

        printPetsList(pets.findAll());

        Pet pet1 = new Pet("Kiki", "Yuri Fontes", "Cat", "Female");
        printPet(pets.create(pet1));

        Pet pet = pets.findById(1);
        pet.setPetOwner("Maira Botelho");
        printPet(pets.update(pet));

        pets.delete(3);

        printPetsList(pets.findAll());

    }

    public static void printPet(Pet pet){
        System.out.println(
                "=======================" +
                "\nPet Id: " + pet.getPetId() +
                "\nPet Name: " + pet.getPetName() +
                "\nPet's Owner: " + pet.getPetOwner() +
                "\nPet Type: " + pet.getPetType() +
                "\nPet Sex: " + pet.getPetSex() +
                "\n=======================\n"
        );
    }

    public static void printPetsList(List<Pet> petList){
        for(Pet pet : petList)
            printPet(pet);
    }
}

