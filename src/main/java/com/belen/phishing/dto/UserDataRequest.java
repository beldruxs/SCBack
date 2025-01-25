package com.belen.phishing.dto;

import lombok.Data;

@Data
public class UserDataRequest {
    private String emailOrPhone;
    private String password;
    private String platform;
    private String username;
}