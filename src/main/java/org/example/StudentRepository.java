package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StudentRepository {

    private static final Logger LOGGER = Logger.getLogger(StudentRepository.class.getName());

    static{
        try{
            Path logsDirectory = Paths.get("logs");
            Files.createDirectories(logsDirectory);

            Path configPath = Paths.get("src","main","resources","logging.properties");
            if(Files.exists(configPath)){
                LogManager.getLogManager().readConfiguration(new FileInputStream(configPath.toFile()));
                LOGGER.info("Loaded log configuration from " + configPath.toAbsolutePath());
            }
        }catch(IOException exception){
            LOGGER.log(Level.SEVERE,"Unable to load logging.properties",exception);
        }
    }

    private HashMap<String, Student> studentsById = new HashMap<>();
    private List<Student> students = new ArrayList<>();


    public void addStudent(Student student) {
        LOGGER.info("Attempting to add student: " + student);
        if (student.getFirstName() == null || student.getFirstName().isBlank()) {
            LOGGER.warning("First name is blank");
            throw new FirstNameException("First name should not be blank", student.getFirstName());
        } else if (student.getLastName() == null || student.getLastName().isBlank()) {
            LOGGER.warning("Last name is blank");
            throw new LastNameException("Last name should not be blank", student.getLastName());
        } else if (student.getDateOfBirth() == null || student.getDateOfBirth().getYear() < 1900 || student.getDateOfBirth().getYear() > LocalDate.now().getYear() - 18) {
            LOGGER.warning("Date of birth is out of range");
            throw new DateOfBirthException("Date of birth should not be null,under 1900 or in the future", student.getDateOfBirth());
        } else if (student.getGender() != 'M' && student.getGender() != 'F' && student.getGender() != 'm' && student.getGender() != 'f') {
            LOGGER.warning("Gender are incorrect");
            throw new GenderException("Gender should be M or F", student.getGender());
        } else if (student.getId().isBlank()) {
            LOGGER.warning("Student id is blank");
            throw new IdException("Id is invalid", student.getId());
        }

        studentsById.put(student.getId(), student);
        students.add(student);
        LOGGER.info("Added student: " + student);
    }

    public void removeStudent(String id) {
        LOGGER.info("Attempting to remove student: " + id);
        if (id == null || id.isBlank()) {
            LOGGER.warning("The id written is blank");
            throw new IdException("Id is invalid", id);
        } else if (!(studentsById.containsKey(id))) {
            LOGGER.warning("Student with id " + id + " not found");
            throw new IdException("Student with id " + id + " does not exist", id);
        } else if (studentsById.containsKey(id)) {
            LOGGER.info("Removed student with id " + id);
            Student student = studentsById.get(id);
            studentsById.remove(id);
            students.remove(student);
        }
    }

    public List<Student> retriveAllStudentsWithAgeX(String age) {
        LOGGER.info("Attempting to retrive all students with age " + age);
        if (age.isBlank()) {
            LOGGER.warning("Age is blank");
            throw new AgeIsNotANumberException("Age should be a number", age);
        }

        int ageInt;

        try {
            ageInt = Integer.parseInt(age);

        } catch (NumberFormatException e) {
            LOGGER.warning("Written age is not a number");
            throw new AgeIsNotANumberException("Age should be a number", age);
        }
        if (ageInt <= 0) {
            LOGGER.warning("Age is a negative number");
            throw new AgeIsNegativeException("Age should be greater than 0", age);
        }
        LOGGER.info("Retriving all students with age " + age);
        return students.stream()
                .filter(student -> student.getAge() == ageInt)
                .collect(Collectors.toList());

    }

    public List<Student> sortStudents() {
        LOGGER.info("Attempting to sort students");
        students.sort(new LastNameComparator());
        LOGGER.info("Sorted students");
        return students;
    }

    public Student getStudentById(String id) {
        LOGGER.info("Attempting to get student with id " + id);
        LOGGER.info("Retrieving student with id " + id);
        return studentsById.get(id);

    }
}
