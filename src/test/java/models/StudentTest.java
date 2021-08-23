package models;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class StudentTest {

    @Test
    public void getDOBStringTest(){
        //given
        Long id = 50L;
        String name = "Thai";
        int grade = 4;
        String school = "Sanford";
        LocalDate dob = LocalDate.of(2011, 3, 11);
        Student student = new Student(id, name, grade, school, dob);
        String expected = "2011-03-11";

        //when
        String actual = student.getDateOfBirthSQLString();

        //then
        Assert.assertEquals(expected, actual);



    }
}
