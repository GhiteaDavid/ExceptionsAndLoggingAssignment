package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.UUID;

public class Student {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private char gender;
    private String id;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, char gender, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.id = id;
    }


    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " , " + dateOfBirth + " , " + gender + " , " + id;
    }
}