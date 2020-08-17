package com.example.demo.controller;

import com.example.demo.model.DTO.RecoverPasswordDto;
import com.example.demo.model.DTO.UsernameVerifyDto;
import com.example.demo.model.User;
import com.example.demo.service.abstraction.UserService;
import com.example.demo.util.exception.user.userException.*;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.enterprise.inject.Stereotype;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Data
@Log4j2
@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity register(@Valid @RequestBody User user, HttpServletRequest request) throws DuplicateUserException {
        log.info(">>>register method:");
        log.info(">>>user ip {}", request.getRemoteAddr());
        userService.register(user);
        log.info(">>>reg is done:");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/forget")
    public ResponseEntity forgetPassword(@RequestParam String username) throws UserNotFoundException {
        log.info(">>>for get password method:");
        userService.sendCode(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resend")
    public ResponseEntity resend(@RequestParam String username) throws UserNotFoundException {
        userService.sendCode(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/recover")
    public ResponseEntity recoverPassword(@Valid @RequestBody RecoverPasswordDto re) throws UserNotFoundException, AccessDeniedException, UnauthorizedException {
        userService.recoverPassword(re.getUsername(), re.getCode(), re.getPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify")
    public ResponseEntity verify(@Valid @RequestBody UsernameVerifyDto u) throws UserNotFoundException, UnverifiedException, AccessDeniedException, UnauthorizedException {
        userService.verify(u.getUsername(), u.getCode());
        return ResponseEntity.ok().build();
    }
}
