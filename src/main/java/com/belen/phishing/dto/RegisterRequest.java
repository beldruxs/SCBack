package com.belen.phishing.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String username;
    private String password;
    private String mail;
    private String telefono;
    private String frecuencia;
}