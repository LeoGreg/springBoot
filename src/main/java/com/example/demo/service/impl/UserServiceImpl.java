package com.example.demo.service.impl;

import com.example.demo.model.Authority;
import com.example.demo.model.ISP.UserStatus;
import com.example.demo.model.User;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.abstraction.UserService;
import com.example.demo.util.authorityId.AuthorityIdes;
import com.example.demo.util.component.MailSenderClient;
import com.example.demo.util.encoder.Md5Encoder;
import com.example.demo.util.exception.user.userException.*;
import com.example.demo.util.exception.user.userExceptionHandler.UserExceptionHandler;
import com.example.demo.util.generator.Generator;
import com.example.demo.util.generator.GeneratorStaticNum;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

import static com.example.demo.util.message.Message.*;

@Data
@Log4j2
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSenderClient mailSenderClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;

    //reg is with verify: below method(reg-connected)
    @Override
    @Transactional
    public void register(User user) throws DuplicateUserException {
        User duplicate = userRepository.getByUsername(user.getUsername());
        UserExceptionHandler.seeIfDuplicated(duplicate);
        Authority authority = authorityRepository.getById(AuthorityIdes.USER_ROLE_ID);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCode(Generator.getRandomDigits(GeneratorStaticNum.RANDOM_NUM));
        user.setStatus(UserStatus.UNVERIFIED);
        user.setAuthorities(Collections.singletonList(authority));
        userRepository.save(user);
        mailSenderClient.sendSimpleMessage(user.getUsername(), CODE_NEEDED_MESSAGE, user.getCode());
    }

    //reg-connected
    @Override
    @Transactional
    public void verify(String username, String code) throws UserNotFoundException, AccessDeniedException {
        User user = userRepository.getByUsername(username);
        UserExceptionHandler.isThereUserWithGivenUsername(user);
        UserExceptionHandler.checkCodes(user.getCode(), code);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User login(String password, String username) throws UnauthorizedException, UserNotFoundException, UnverifiedException {
        User user = userRepository.getByUsername(username);
        UserExceptionHandler.isThereUserWithGivenUsername(user);
        UserExceptionHandler.ifUserLocked(user.getStatus());
        UserExceptionHandler.checkPassword(password, user.getPassword());
        return user;
    }

    @Override
    @Transactional
    public User changePassword(String username, String password, String newPassword) throws UserNotFoundException, UnauthorizedException {
        User user = userRepository.getByUsername(username);
        UserExceptionHandler.isThereUserWithGivenUsername(user);
        UserExceptionHandler.checkPassword(password, newPassword);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return user;
    }

    //this method works with below method: rec-pass
    @Override
    @Transactional
    public void sendCode(String username) throws UserNotFoundException {
        User user = userRepository.getByUsername(username);
        UserExceptionHandler.isThereUserWithGivenUsername(user);
        user.setCode(Generator.getRandomDigits(GeneratorStaticNum.RANDOM_NUM));
        userRepository.save(user);
        mailSenderClient.sendSimpleMessage(username, CODE_NEEDED_MESSAGE, user.getCode());

    }

    //rec-pass
    @Override
    @Transactional
    public void recoverPassword(String username, String code, String password) throws UserNotFoundException, AccessDeniedException {
        User user = userRepository.getByUsername(username);
        UserExceptionHandler.isThereUserWithGivenUsername(user);
        UserExceptionHandler.checkCodes(user.getCode(), code);
        user.setPassword(passwordEncoder.encode(password));
    }
}
