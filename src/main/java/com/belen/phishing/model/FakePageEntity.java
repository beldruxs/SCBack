package com.belen.phishing.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fakePages")
@Data
@NoArgsConstructor
public class FakePageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "logo", columnDefinition = "TEXT")
    private String logo;

    @Column(name = "visitas")
    private Integer visitas;

    @Column(name = "picks")
    private Integer picks;
}
