package models;

import java.util.Date;

public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String favColor;
    private Date birthday;
    private String job;

    public User() {}

    public User(String firstName, String lastName, String favColor, Date birthday, String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.favColor = favColor;
        this.birthday = birthday;
        this.job = job;
    }

    public User(Integer id, String firstName, String lastName, String favColor, Date birthday, String job) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.favColor = favColor;
        this.birthday = birthday;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFavColor() {
        return favColor;
    }

    public void setFavColor(String favColor) {
        this.favColor = favColor;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return String.format("%s %s\t|\t%s\t|\t%s\t|\t%s",
                this.getFirstName(), this.getLastName(), this.getFavColor(), this.getJob(), this.getBirthday());
    }
}
