package com.tamaraw.payrollbackend.exceptions;

public class NoUserException extends RuntimeException{
    public NoUserException(String message) {
        super(message);
    }
}
