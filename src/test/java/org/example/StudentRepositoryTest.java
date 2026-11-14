package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {


    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepository();
    }

    @Test
    void addStudentHappyPath() {
        Student student = new Student("Maria", "Ionescu", LocalDate.of(2000, 7, 22), 'F', "6000722400028");
        studentRepository.addStudent(student);
        assertEquals(student, studentRepository.getStudentById("6000722400028"));
    }

    @Test
    void addStudentWithFirstNameInvalidTest() {
        Student student = new Student("", "Popescu", LocalDate.of(2001, 5, 19), 'M', "129471927327359");
        assertThrows(FirstNameException.class, () -> studentRepository.addStudent(student));
    }

    @Test
    void addStudentWithFirstNameNullTest() {
        Student student = new Student(null, "Popescu", LocalDate.of(2001, 5, 19), 'M', "129471927327359");
        assertThrows(FirstNameException.class, () -> studentRepository.addStudent(student));
    }

    @Test
    void addStudentWithLastNameInvalidTest() {
        Student student = new Student("Alex", "", LocalDate.of(2001, 5, 19), 'M', "129471927327359");
        assertThrows(LastNameException.class, () -> studentRepository.addStudent(student));
    }

    @Test
    void addStudentWithLastNameNullTest() {
        Student student = new Student("Alex", null, LocalDate.of(2001, 5, 19), 'M', "129471927327359");
        assertThrows(LastNameException.class, () -> studentRepository.addStudent(student));
    }

    @Test
    void addStudentWithDateOfBirthUnder1900InvalidTest() {
        Student student = new Student("Mihai", "Popescu", LocalDate.of(1899, 5, 18), 'M', "129471927327359");
        assertThrows(DateOfBirthException.class, () -> studentRepository.addStudent(student));
    }

    @Test
    void addStudentWithAgeUnder18InvalidTest() {
        Student student = new Student("Mihai", "Popescu", LocalDate.of(2024, 5, 18), 'M', "129471927327359");
        assertThrows(DateOfBirthException.class, () -> studentRepository.addStudent(student));
    }

    @Test
    void addStudentWithDateOfBirthNullTest() {
        Student student = new Student("Mihai", "Popescu", null, 'M', "129471927327359");
        assertThrows(DateOfBirthException.class, () -> studentRepository.addStudent(student));
    }

    @Test
    void idInvalidTest() {
        Student student = new Student("Mircea", "Popescu", LocalDate.of(2001, 9, 20), 'M', "");
        assertThrows(IdException.class, () -> studentRepository.addStudent(student));
    }

    @Test
    void genderInvalidTest() {
        Student student = new Student("Mircea", "Popescu", LocalDate.of(2001, 9, 20), 'k', "1294727942979");
        assertThrows(GenderException.class, () -> studentRepository.addStudent(student));
    }

    @Test
    void genderLowerCaseTest() {
        Student student = new Student("Mircea", "Popescu", LocalDate.of(2001, 9, 20), 'm', "1294727942979");
        studentRepository.addStudent(student);
        assertEquals(student, studentRepository.getStudentById("1294727942979"));
    }

    @Test
    void removeStudentHappyPath() {
        Student student = new Student("Mircea", "Popescu", LocalDate.of(2001, 9, 20), 'M', "120598120958");
        studentRepository.addStudent(student);
        assertEquals(student, studentRepository.getStudentById("120598120958"));
        studentRepository.removeStudent("120598120958");
        assertThrows(IdException.class, () -> studentRepository.removeStudent("120598120958"));
    }

    @Test
    void removeStudentWithIdInvalidTest() {
        Student student = new Student("Mircea", "Popescu", LocalDate.of(2001, 9, 20), 'M', "325293857235");
        studentRepository.addStudent(student);
        assertThrows(IdException.class, () -> studentRepository.removeStudent(""));
    }

    @Test
    void retriveAllStudentsWithAgeXTestHappyPath() {
        List<Student> result = new ArrayList<>();
        Student s1 = new Student("Andrei", "Popescu", LocalDate.of(2001, 3, 14), 'M', "5010314400011");
        Student s2 = new Student("Maria", "Ionescu", LocalDate.of(2002, 7, 22), 'F', "6000722400028");
        Student s3 = new Student("Alexandru", "Dumitrescu", LocalDate.of(2002, 7, 22), 'M', "5021105400037");

        studentRepository.addStudent(s1);
        studentRepository.addStudent(s2);
        studentRepository.addStudent(s3);

        result.add(s2);
        result.add(s3);
        assertEquals(result, studentRepository.retriveAllStudentsWithAgeX("24"));
    }

    @Test
    void retriveAllStudentsWithAgeXUnder0Test() {
        assertThrows(AgeIsNegativeException.class, () -> studentRepository.retriveAllStudentsWithAgeX("-24"));
    }

    @Test
    void retriveAllStudentsWithAgeThatIsNotANumberTest() {
        assertThrows(AgeIsNotANumberException.class, () -> studentRepository.retriveAllStudentsWithAgeX("aaa"));
    }

    @Test
    void sortStudentsByLastNameTest() {
        List<Student> result = new ArrayList<>();
        Student s1 = new Student("Andrei", "Popescu", LocalDate.of(2001, 3, 14), 'M', "5010314400011");
        Student s2 = new Student("Maria", "Ionescu", LocalDate.of(2002, 7, 22), 'F', "6000722400028");
        Student s3 = new Student("Alexandru", "Dumitrescu", LocalDate.of(2002, 7, 22), 'M', "5021105400037");
        studentRepository.addStudent(s1);
        studentRepository.addStudent(s2);
        studentRepository.addStudent(s3);

        studentRepository.sortStudents();

        result.add(s3);
        result.add(s2);
        result.add(s1);
        assertEquals("Dumitrescu", result.get(0).getLastName());
        assertEquals("Ionescu", result.get(1).getLastName());
        assertEquals("Popescu", result.get(2).getLastName());

    }


}