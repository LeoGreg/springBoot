package com.example.demo.util.exception.user.userException;

import org.springframework.security.authentication.LockedException;

public class UnauthorizedException extends Exception {

    public UnauthorizedException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws UnauthorizedException {
        if (ex) {
            throw new UnauthorizedException(message);
        }
    }
}