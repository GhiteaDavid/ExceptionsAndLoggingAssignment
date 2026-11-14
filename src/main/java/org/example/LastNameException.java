package org.example;

public class LastNameException extends RuntimeException {

    private String lastName;
    public LastNameException(String message,String lastName) {
        super(message);
        this.lastName = lastName;
    }
}
