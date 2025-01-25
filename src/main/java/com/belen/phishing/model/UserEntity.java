package com.belen.phishing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "mail")
    private String mail;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "frecuencia")
    private String frecuencia;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "puntos")
    private Integer puntos;

    @Column(name = "lNotificable")
    private boolean lNotificable;

    @Column(name = "N_PHISHING_RECIBIDO")
    private Integer nPhishingRecibido;

    @Column(name = "N_PHISHING_ENTRADO")
    private Integer nPhishingEntrado;

    @Column(name = "N_PHISHING_PICADO")
    private Integer nPhishingPicado;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> (GrantedAuthority) role::getName)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    protected void onCreate() {
        lNotificable = true;
        if (puntos == null) {
            puntos = 0;
        }
        if (nPhishingRecibido == null) {
            nPhishingRecibido = 0;
        }
        if (nPhishingEntrado == null) {
            nPhishingEntrado = 0;
        }
        if (nPhishingPicado == null) {
            nPhishingPicado = 0;
        }
    }
}