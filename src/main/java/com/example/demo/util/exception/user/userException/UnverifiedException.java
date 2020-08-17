package com.example.demo.util.exception.user.userException;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UnverifiedException extends Exception {

    public UnverifiedException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws UnverifiedException {
        if (ex) {
            throw new UnverifiedException(message);
        }
    }
}

