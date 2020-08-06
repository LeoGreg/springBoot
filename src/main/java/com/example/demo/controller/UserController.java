package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.abstraction.UserService;
import com.example.demo.util.exception.user.userException.DuplicateUserException;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Data
@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/reg")
    public ResponseEntity register(@Valid @RequestBody User user, HttpServletRequest request) throws DuplicateUserException, RuntimeException {
        log.info(">>> in controller register:");
        log.info(">>>request ip {}",request.getRemoteAddr());
        return ResponseEntity.ok(userService.register(user));
    }
}
