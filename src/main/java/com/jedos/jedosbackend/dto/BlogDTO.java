package com.jedos.jedosbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BlogDTO {

    private int id;
    private String codBlog;
    private String title;
    private String imageUrl;
    private String description;
    private Integer visitas;
    private LocalDateTime createdAt;
}
