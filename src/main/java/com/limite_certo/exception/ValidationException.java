package com.limite_certo.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message) {
        super(message);
    }
}
