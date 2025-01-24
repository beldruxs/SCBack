package com.belen.phishing.service;

import com.belen.phishing.dto.RegisterRequest;
import com.belen.phishing.model.Role;
import com.belen.phishing.model.UserEntity;
import com.belen.phishing.repository.PlantillaRepository;
import com.belen.phishing.repository.RoleRepository;
import com.belen.phishing.repository.UserRepository;
import com.belen.phishing.config.ConstantesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import java.util.Collections;

@Service
public class AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;
    private PlantillaRepository plantillaRepository;

    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                       EmailService emailService, PlantillaRepository plantillaRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.plantillaRepository = plantillaRepository;
    }

    public ResponseEntity<String> register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setNombre(registerRequest.getNombre());
        user.setApellido1(registerRequest.getApellido1());
        user.setApellido2(registerRequest.getApellido2());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setMail(registerRequest.getMail());
        user.setTelefono(registerRequest.getTelefono());
        user.setFrecuencia(registerRequest.getFrecuencia());

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        // Fetch the HTML template
        String htmlTemplate = plantillaRepository.findHtmlByCodPlantilla(ConstantesUtil.REGISTER_HTML);
        // Replace the placeholder with the user's name
        String personalizedHtml = htmlTemplate.replace("{{nombre}}", user.getNombre());

        // Send the email
        try {
            emailService.sendEmail2(user.getMail(), "Bienvenido a Safe Click", personalizedHtml);
        } catch (MessagingException e) {
            return new ResponseEntity<>("User registered but failed to send email: " + e.getMessage(), HttpStatus.OK);
        }

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }
}