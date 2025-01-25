package com.belen.phishing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhishingRequest {

    private String username;
    private String mail;
    private String platform;

}
