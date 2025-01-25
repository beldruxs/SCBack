package com.belen.phishing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String username;
    private String mail;
    private String telefono;
    private String profileImg;
    private Integer puntos;
    private Boolean lNotificable;
}