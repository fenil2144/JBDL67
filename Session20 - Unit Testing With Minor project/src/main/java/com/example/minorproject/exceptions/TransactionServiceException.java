package com.example.minorproject.exceptions;

public class TransactionServiceException extends RuntimeException{

    public TransactionServiceException(String message) {
        super(message);
    }
}
