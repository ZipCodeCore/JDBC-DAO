package models;

import java.time.LocalDate;

public class Student {

    private Long id;
    private  String name;
    private Integer grade;
    private String school;
    private LocalDate dateOfBirth;
    private String location;

    public Student() {
    }

    public Student(Long id, String name, Integer grade, String school, LocalDate dateOfBirth){
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.school = school;
        this.dateOfBirth = dateOfBirth;
    }

    public Student (Long id, String name, Integer grade, String school, LocalDate dateOfBirth, String location){
        this(id, name, grade, school, dateOfBirth);
        this.location = location;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override //add JSON ObjectMapper exception here
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", school='" + school + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", location='" + location + '\'' +
                '}';
    }
}
