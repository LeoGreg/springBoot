package com.example.demo.util.hessian;

import com.example.demo.util.exception.user.userException.UserNotFoundException;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws NotFoundException {
        if (ex) {
            throw new NotFoundException(message);
        }
    }

}
