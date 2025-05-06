package daos;

import models.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements Repo{

    private Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public void create(Student student) {
        executeStatement(String.format(new StringBuilder()
                        .append("INSERT INTO IAODataTest.students(")
                        .append("id, name, grade, school, DOB, age) ")
                        .append("VALUES (%s, '%s', %s, '%s', DATE '%s', %s);")
                        .toString(),
                student.getId(),
                student.getName(),
                student.getGrade(),
                student.getSchool(),
                student.getDateOfBirthSQLString(),
                student.getAge()));  //age will appear in table but not in resultSet;
    }

    public List<Student> readAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM IAODataTest.students;");
        List<Student> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String grade = resultSet.getString(3);
                String school = resultSet.getString(4);
                String dob = resultSet.getString(5);
                String age = resultSet.getString(6);
                list.add(new Student (
                        Long.parseLong(id),
                        name,
                        Integer.parseInt(grade),
                        school,
                        LocalDate.parse(dob),
                        Integer.parseInt(age)));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return list;
    }


    public Student read(Long studentId) {
        return readAll()
                .stream()
                .filter(student -> student.getId().equals(studentId))
                .findAny()
                .get();
    }

    public void updateId(Long newId, Student newStudentData) {
        Long reset = newId;
        Long find = newStudentData.getId();
        executeStatement(String.format(new StringBuilder()
                .append("UPDATE students ")
                .append("SET id = %s ")
                .append("WHERE id = %s;")
                .toString(),
                reset,
                find));


    }

    public void updateSchool(String newSchool, Student newStudentData){
        String reset = newSchool;
        Long find = newStudentData.getId();
        executeStatement(String.format(new StringBuilder()
                        .append("UPDATE students ")
                        .append("SET school = '%s' ")
                        .append("WHERE id = %s;")
                        .toString(),
                reset,
                find));

    }

    public  void updateBirthday(LocalDate date, Student newStudentData){
        String reset = getDOBString(date);
        Long find = newStudentData.getId();
        executeStatement(String.format(new StringBuilder()
                        .append("UPDATE students ")
                        .append("SET dob = DATE '%s' ")
                        .append("WHERE id = %s;")
                        .toString(),
                reset,
                find));

    }

    public String getDOBString(LocalDate date){
        String joined = "";
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

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

    public void delete(Long id) {
        Long deleteThis = id;
        executeStatement(String.format(new StringBuilder()
                .append("DELETE FROM students WHERE id = %s;")
                .toString(),
                id));

    }

    public void delete(Student student) {
        Long find = student.getId();
        delete(find);

    }
}
