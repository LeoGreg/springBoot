package com.example.demo.model.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class ChangePasswordDto {

    @NotBlank
    private String username;
    @NotBlank
    private String newPassword;
    @NotBlank
    private String password;
}
