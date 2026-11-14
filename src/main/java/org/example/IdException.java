package org.example;

public class IdException extends RuntimeException {
    String id;
    public IdException(String message,String id) {
        super(message);
        this.id = id;
    }
}
