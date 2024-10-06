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

    @PostMapping("/submit")
    public ResponseEntity<Void> submitContactForm(
            @RequestBody ContactFormDTO contactForm) {
        // Send confirmation email to the user
        String userSubject = "Petición de contacto JEDOS STUDIO";
        String userText = "Gracias por ponerte en contacto con nosotros, " + contactForm.getName() + ", hemos recibido tu mensaje: \"" + contactForm.getMessage() + "\". En breves, nos pondremos en contacto contigo.";
        emailService.sendEmail(contactForm.getEmail(), userSubject, userText);

        // Send email to the admin
        String adminSubject = "JEDOS STUDIO";
        String adminText = "Name: " + contactForm.getName() + "\nEmail: " + contactForm.getEmail() + "\nMessage: " + contactForm.getMessage();
        emailService.sendEmail("antonio.saborido01@gmail.com", adminSubject, adminText);
        emailService.sendEmail("pablo.viana.servan99@gmail.com", adminSubject, adminText);
        return ResponseEntity.ok().build();
    }
}