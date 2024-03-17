package com.example.jpaDemo.exceptions;

public class BadPersonRequestException extends RuntimeException{
    public BadPersonRequestException(String message) {
        super(message);
    }
}
