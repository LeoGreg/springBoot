package com.example.demo.controller;

import com.example.demo.model.DTO.*;
import com.example.demo.model.User;
import com.example.demo.service.abstraction.UserService;
import com.example.demo.util.exception.user.userException.*;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Data
@Log4j2
@RestController
@RequestMapping("/api/accounts")
public class AccountsController {



    @Autowired
    private UserService userService;

 /*   @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginPasswordDto login) throws UserNotFoundException, UnauthorizedException, UnverifiedException {
        User user = userService.login(login.getPassword(), login.getUsername());
        return ResponseEntity.ok(user);
    }*/

    @PostMapping("/changePassword")
    public ResponseEntity change(@Valid @RequestBody ChangePasswordDto c) throws UnauthorizedException, UserNotFoundException, UnverifiedException {
        return ResponseEntity.ok(userService.changePassword(c.getUsername(), c.getPassword(), c.getNewPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user, HttpServletRequest request) throws DuplicateUserException {
        log.info(">>>register method:");
        log.info(">>>user ip {}", request.getRemoteAddr());
        userService.register(user);
        log.info(">>>reg is done:");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/forget")
    public ResponseEntity forgetPassword(@RequestBody ForgetResendDto f) throws UserNotFoundException {
        log.info(">>>for get password method: username", f.getUsername());
        userService.sendCode(f.getUsername());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resend")
    public ResponseEntity resend(@RequestBody ForgetResendDto f) throws UserNotFoundException {
        userService.sendCode(f.getUsername());
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
