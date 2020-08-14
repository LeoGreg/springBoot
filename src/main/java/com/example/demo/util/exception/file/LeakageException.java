package com.example.demo.util.exception.file;

import com.example.demo.util.exception.user.userException.DuplicateUserException;

public class LeakageException extends Exception {

    public LeakageException(String message) {
        super(message);
    }

    public static void isOutOfLimit(boolean ex, String message) throws LeakageException {
        if (ex) {
            throw new LeakageException(message);
        }
    }
}