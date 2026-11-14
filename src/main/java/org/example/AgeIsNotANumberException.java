package org.example;

public class AgeIsNotANumberException extends RuntimeException {
    String age;

    public AgeIsNotANumberException(String message, String age) {
        super(message);
        this.age = age;
    }
}
