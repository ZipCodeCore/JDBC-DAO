package daos;

import models.Connections;
import models.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetsConcreteDao implements PetsDao {

    Connection connection = Connections.getConnection();

    public Pet findById(int id) {
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pets WHERE pet_id=" + id);

            if(rs.next())
                return extractPetFromResultSet(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private Pet extractPetFromResultSet(ResultSet rs) throws SQLException {

        Pet pet = new Pet();

        pet.setPetId( rs.getInt("pet_id") );
        pet.setPetName( rs.getString("pet_name") );
        pet.setPetOwner( rs.getString("pet_owner") );
        pet.setPetType( rs.getString("pet_type"));
        pet.setPetSex( rs.getString( "pet_sex"));
        return pet;
    }

    public List<Pet> findAll() {


        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pets;");
            List<Pet> pets = new ArrayList<Pet>();

            while(rs.next())
            {
                Pet pet = extractPetFromResultSet(rs);
                pets.add(pet);
            }

            return pets;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Pet update(Pet pet) {

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE pets SET pet_name=?, pet_owner=?, " +
                    "pet_type=?, pet_sex=? WHERE pet_id=?");
            ps.setString(1, pet.getPetName());
            ps.setString(2, pet.getPetOwner());
            ps.setString(3, pet.getPetType());
            ps.setString(4, pet.getPetSex());
            ps.setInt(5, pet.getPetId());
            int i = ps.executeUpdate();

            if(i == 1) {
                return findById(pet.getPetId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Pet create(Pet pet) {

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO pets VALUES (NULL, ?, ?, ?, ?)");
            ps.setString(1, pet.getPetName());
            ps.setString(2, pet.getPetOwner());
            ps.setString(3, pet.getPetType());
            ps.setString(4, pet.getPetSex());
            int i = ps.executeUpdate();

            if(i == 5) {

                return findById(pet.getPetId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void delete(int id) {

        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM pets WHERE pet_id=" + id);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
