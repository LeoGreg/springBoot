package com.example.demo.model.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UsernameVerifyDto {
    @NotBlank
    private String username;
    @NotBlank
    private String code;
}
