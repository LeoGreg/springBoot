package com.example.demo.util.exception.user.userException;

public class DuplicateUserException extends Exception {

    public DuplicateUserException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws DuplicateUserException {
        if (ex) {
            throw new DuplicateUserException(message);
        }
    }
}