package models;

public class User implements DTO {

    private Integer id;
    private String name;
    private String pwd;
    private String email;
    private String phoneNumber;

// ---- constructors ---------------------
    public User() {
    }

    public User(String name, String pass) {
        this.name = name;
        this.pwd = pass;
    }

    public User(Integer id, String name, String pass, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.pwd = pass;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

// --------- getters & setters -------------

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pwd;
    }
    public void setPass(String pass) {
        this.pwd = pass;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
