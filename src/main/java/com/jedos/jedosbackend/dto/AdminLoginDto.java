package com.jedos.jedosbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class AdminLoginDto {
    private String username;
    private String password;
    private String otpCode;
}