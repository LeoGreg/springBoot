package com.example.demo.util.exception.user.userExceptionHandler;

import com.example.demo.model.User;
import com.example.demo.util.exception.user.userException.DuplicateUserException;

public class UserExceptionHandler {

    public static void seeIfDuplicated(User user) throws DuplicateUserException {
        DuplicateUserException.check(user != null, "there is already user with given username");
    }
}
