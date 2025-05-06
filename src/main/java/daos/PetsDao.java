package daos;

import models.Pet;

import java.util.List;

interface PetsDao {
    public Pet findById(int id);
    public List findAll();
    public Pet update(Pet pet);
    public Pet create(Pet pet);
    public void delete(int id);

}
