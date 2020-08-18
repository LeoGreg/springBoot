package com.example.demo.model.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginPasswordDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
