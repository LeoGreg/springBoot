package com.example.demo.util.exception.file;

public class NoDirectoryException extends RuntimeException {
    public NoDirectoryException(String message) {
        super(message);
    }

    public NoDirectoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
