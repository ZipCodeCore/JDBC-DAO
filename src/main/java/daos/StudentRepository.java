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
                list.add(new Student (
                        Long.parseLong(id),
                        name,
                        Integer.parseInt(grade),
                        school,
                        LocalDate.parse(dob)));
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

    public void updateId(Long id, Student newStudentData) {
        Long reset = id;
        Long find = newStudentData.getId();
        executeStatement(String.format(new StringBuilder()
                .append("UPDATE students")
                .append("SET id = %s")
                .append("WHERE id = %s;")
                .toString(),
                reset,
                find));


    }

    public void delete(Long id) {
        Long deleteThis = id;
        executeStatement(String.format(new StringBuilder()
                .append("DELETE * FROM students WHERE id = %s;")
                .toString(),
                id));

    }

    public void delete(Student pokemon) {
        Long find = pokemon.getId();
        delete(find);

    }
}
