package models;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringJoiner;

public class Student {

    private int age;
    private Long id;
    private  String name;
    private Integer grade;
    private String school;
    private LocalDate dateOfBirth;
    private String location;

    public Student() {
    }

    public Student(Long id, String name, Integer grade, String school){
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.school = school;
        //this.dateOfBirth = dateOfBirth;
    }

    public Student(Long id, String name, Integer grade, String school, LocalDate dateOfBirth){
        this(id, name, grade, school);
        this.dateOfBirth = dateOfBirth;
        this.age = getAge();
    }

    public Student (Long id, String name, Integer grade, String school, LocalDate dateOfBirth, String location){
        this(id, name, grade, school, dateOfBirth);
        this.location = location;
    }

//    public Student(Long id, String name, Integer grade, String school, int age) {
//        this(id, name, grade, school);
//        this.age = age;



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

    public String getDateOfBirthSQLString() {
        String toDate = getDOBString();
        return toDate;
    }

    public String getDOBString(){
        String joined = "";
        int year = dateOfBirth.getYear();
        int month = dateOfBirth.getMonthValue();
        int day = dateOfBirth.getDayOfMonth();

        if (month < 10 && day < 10){
            joined = String.format("%s-0%s-0%s", year, month, day);
        } else if (month < 10){
            joined = String.format("%s-0%d-%s", year, month, day);
        } else if (day < 10) {
            joined = String.format("%s-%s-0%s", year, month, day);
        } else

         joined = String.format("%s-%s-%s", year, month, day);


        return joined;

    }

    public Integer getAge() {
        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        return age;
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
