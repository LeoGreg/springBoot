package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.abstraction.UserService;
import com.example.demo.util.component.MailSenderClient;
import com.example.demo.util.encoder.Md5Encoder;
import com.example.demo.util.exception.user.userException.DuplicateUserException;
import com.example.demo.util.exception.user.userExceptionHandler.UserExceptionHandler;
import com.example.demo.util.generator.Generator;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Data
@Log4j2
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSenderClient mailSenderClient;

    @Override
    @Transactional
    public User register(User user) throws DuplicateUserException, RuntimeException {
        log.info(">>>user is registering:");
        User duplicated = userRepository.getByUsername(user.getUsername());
        UserExceptionHandler.seeIfDuplicated(duplicated);
        user.setPassword(Md5Encoder.encode(user.getPassword()));
        user.setStatus(0);
        user.setCode(Generator.getRandomDigits(5));
        User DBSaved = userRepository.save(user);
        mailSenderClient.sendSimpleMessage(user.getUsername(), "for fulfilling register request please type this code:", user.getCode());
        log.info(">>>user code : {}", user.getCode());
        return DBSaved;
    }

}
