package com.belen.phishing.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "fakeAttempts")
@Data
@NoArgsConstructor
public class FakeAttemptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "lEnviado")
    private boolean lEnviado;

    @Column(name = "lEntrado")
    private boolean lEntrado;

    @Column(name = "lDatosIntroducidos")
    private boolean lDatosIntroducidos;

    @Column(name = "fEnvio", updatable = false)
    private LocalDateTime fEnvio;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "plantilla_id", referencedColumnName = "id", nullable = true)
    private PlantillaEntity plantilla;

    @PrePersist
    protected void onCreate() {
        fEnvio = LocalDateTime.now();
    }

}