package com.jedos.jedosbackend.controllers;

import com.jedos.jedosbackend.dto.ContactFormDTO;
import com.jedos.jedosbackend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactFormController {

    private final EmailService emailService;

    @Autowired
    public ContactFormController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/submit")
    public ResponseEntity<Void> submitContactForm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message) {
        // Send confirmation email to the user
        String userSubject = "Petición de contacto JEDOS STUDIO";
        String userText = "Gracias por ponerte en contacto con nosotros, " + name + ", hemos recibido tu mensaje: \"" + message + "\". En breves, nos pondremos en contacto contigo.";
        emailService.sendEmail(email, userSubject, userText);

        // Send email to the admin
        String adminSubject = "JEDOS STUDIO";
        String adminText = "Name: " + name + "\nEmail: " + email + "\nMessage: " + message;
        emailService.sendEmail("antonio.saborido01@gmail.com", adminSubject, adminText);
        emailService.sendEmail("pablo.viana.servan99@gmail.com", adminSubject, adminText);
        return ResponseEntity.ok().build();
    }
}