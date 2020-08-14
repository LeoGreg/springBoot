package com.example.demo.util.exception.file;

import com.example.demo.util.exception.user.userException.DuplicateUserException;

public class NoResourceException extends Exception {

    public NoResourceException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws NoResourceException {
        if (ex) {
            throw new NoResourceException(message);
        }
    }
}
