package com.jedos.jedosbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceDTO {
    private int id;
    private String title;
    private String titulo;
    private String description;
    private String descripcion;
    private Double price;
    private Boolean reccurente;
}