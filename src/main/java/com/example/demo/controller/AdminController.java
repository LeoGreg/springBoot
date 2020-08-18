package com.example.demo.controller;

import com.example.demo.model.DTO.ForgetResendDto;
import com.example.demo.service.abstraction.UserService;
import com.example.demo.util.constants.Constants;
import com.example.demo.util.exception.user.userException.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RolesAllowed(Constants.ADMIN)
    @PostMapping("/make")
    public ResponseEntity makeAdmin(@RequestBody ForgetResendDto f) throws UserNotFoundException {
        userService.makeAdmin(f.getUsername());
        return ResponseEntity.ok().build();
    }
}
