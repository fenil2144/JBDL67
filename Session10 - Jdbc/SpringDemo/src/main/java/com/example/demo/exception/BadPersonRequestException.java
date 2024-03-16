package com.example.demo.exception;

public class BadPersonRequestException extends RuntimeException{

    public BadPersonRequestException(String message) {
        super(message);
    }
}
