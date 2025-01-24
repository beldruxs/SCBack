package com.belen.phishing.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plantillas")
@Data
@NoArgsConstructor
public class PlantillaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cod-plantilla", nullable = false)
    private String codPlantilla;

    @Column(name = "html", columnDefinition = "TEXT", nullable = false, length = 30000)
    private String html;

    @ManyToOne
    @JoinColumn(name = "fake_page_id", nullable = true)
    private FakePageEntity fakePage;
}