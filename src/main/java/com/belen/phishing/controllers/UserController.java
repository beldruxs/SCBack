package com.belen.phishing.controllers;

import com.belen.phishing.model.UserEntity;
import com.belen.phishing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {
        Optional<UserEntity> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserEntity user = userOptional.get(); // Obtiene el UserEntity del Optional
        Map<String, Object> response = new HashMap<>();
        response.put("nombre", user.getNombre());
        response.put("apellido", user.getApellido());
        response.put("mail", user.getMail());
        response.put("profileImg", user.getProfileImg());
        return ResponseEntity.ok(response);
    }
}