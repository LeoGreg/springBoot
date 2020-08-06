package com.example.demo.service.abstraction;

import com.example.demo.model.User;
import com.example.demo.util.exception.user.userException.DuplicateUserException;

import javax.transaction.Transactional;

public interface UserService {
    @Transactional
    User register(User user) throws DuplicateUserException,RuntimeException;
}
