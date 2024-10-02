package com.jedos.jedosbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactFormDTO {
    private String name;
    private String email;
    private String message;
}
