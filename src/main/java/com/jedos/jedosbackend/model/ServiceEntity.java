package com.jedos.jedosbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", columnDefinition = "TINYTEXT COLLATE 'latin1_spanish_ci'")
    private String title;

    @Column(name = "titulo", columnDefinition = "TINYTEXT COLLATE 'latin1_spanish_ci'")
    private String titulo;

    @Column(name = "description", columnDefinition = "TINYTEXT COLLATE 'latin1_spanish_ci'")
    private String description;

    @Column(name = "descripcion", columnDefinition = "TINYTEXT COLLATE 'latin1_spanish_ci'")
    private String descripcion;

    @Column(name = "price")
    private Double price;

    @Column(name = "reccurente")
    private Boolean reccurente;
}