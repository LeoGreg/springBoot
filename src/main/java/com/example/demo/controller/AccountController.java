package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.faces.annotation.RequestMap;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @GetMapping("/test")
    public ResponseEntity test1(){
        return ResponseEntity.ok("Hello from test1");
    }
}
