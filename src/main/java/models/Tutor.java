package models;

public class Tutor {

    private Long id;
    private String name;
    private Specialty specialty;
    private String availability;  //can you add multiple enum fields to a database column?
    private Preference preference;


    private enum Specialty{
        PRESCHOOL, PRIMARY, SECONDARY, POST_SECONDARY;
    }

    private enum Preference{
        VIRTUAL, IN_PERSON;
    }

    public Tutor(Long id, String name, Specialty specialty, String availability, Preference preference){
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.availability = availability;
        this.preference = preference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialty=" + specialty +
                ", availability='" + availability + '\'' +
                ", preference=" + preference +
                '}';
    }
}
