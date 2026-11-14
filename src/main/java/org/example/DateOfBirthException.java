package org.example;

import java.time.LocalDate;

public class DateOfBirthException extends RuntimeException {
    LocalDate dateOfBirth;
    public DateOfBirthException(String message,LocalDate dateOfBirth)
    {
        super(message);
        this.dateOfBirth = dateOfBirth;
    }
}
