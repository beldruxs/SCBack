package com.belen.phishing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private String nombre;
    private Integer puntos;
    private Integer recibido;
    private Integer entrado;
    private Integer enviado;
}