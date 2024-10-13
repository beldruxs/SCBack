package com.jedos.jedosbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class TwoFactorDTO {
    private Integer userId;
    private String code;

}
