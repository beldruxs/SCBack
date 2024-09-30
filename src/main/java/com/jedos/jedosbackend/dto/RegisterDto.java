package com.jedos.jedosbackend.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private String mail;
    private String profileImg;
}