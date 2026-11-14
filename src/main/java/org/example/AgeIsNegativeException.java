package org.example;

public class AgeIsNegativeException extends RuntimeException {
    String age;

    public AgeIsNegativeException(String message, String age) {
        super(message);
        this.age = age;
    }
}
