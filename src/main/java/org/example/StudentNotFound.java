package org.example;

public class StudentNotFound extends RuntimeException {
    int id;
    public StudentNotFound(String message,int id) {
        super(message);
        this.id=id;
    }
}
