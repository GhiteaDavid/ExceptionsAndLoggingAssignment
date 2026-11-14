package org.example;

public class FirstNameException extends RuntimeException {
    private String firstName;

    public FirstNameException(String message,String firstName) {
        super(message);
        this.firstName = firstName;
    }
}
