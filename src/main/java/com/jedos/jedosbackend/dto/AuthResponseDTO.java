package com.jedos.jedosbackend.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private Integer idUser;

    public AuthResponseDTO(String accessToken, Integer idUser) {
        this.accessToken = accessToken;
        this.idUser = idUser;
    }
}