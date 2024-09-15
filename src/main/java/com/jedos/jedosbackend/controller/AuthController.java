package com.jedos.jedosbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jedos.jedosbackend.model.User;
import com.jedos.jedosbackend.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // Configura según tu frontend
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if(userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email ya en uso.");
        }
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    // Puedes agregar métodos de login si usas JWT o sesiones
}