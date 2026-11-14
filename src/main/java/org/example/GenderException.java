package org.example;

public class GenderException extends RuntimeException {
    char gender;
    public GenderException(String message, char gender) {
        super(message);
        this.gender=gender;
    }
}
