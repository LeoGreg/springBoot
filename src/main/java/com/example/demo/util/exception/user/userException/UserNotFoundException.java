package com.example.demo.util.exception.user.userException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws UserNotFoundException {
        if (ex) {
            throw new UserNotFoundException(message);
        }
    }
}
