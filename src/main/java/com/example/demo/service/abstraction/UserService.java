package com.example.demo.service.abstraction;

import com.example.demo.model.User;
import com.example.demo.util.exception.user.userException.*;

import javax.transaction.Transactional;

public interface UserService {

    @Transactional
    void register(User user) throws DuplicateUserException;

    @Transactional
    void verify(String username, String code) throws UserNotFoundException, UnverifiedException, UnauthorizedException, AccessDeniedException;

    @Transactional
    User login(String password, String username) throws UnauthorizedException, UserNotFoundException, UnverifiedException;

    User changePassword(String username, String password, String newPassword) throws UserNotFoundException, UnauthorizedException;

    @Transactional
    void sendCode(String username) throws UserNotFoundException;

    //rec-pass
    @Transactional
    void recoverPassword(String username, String code, String password) throws UserNotFoundException, UnauthorizedException, AccessDeniedException;
}
