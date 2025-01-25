package com.belen.phishing.controllers;

import com.belen.phishing.dto.UserDTO;
import com.belen.phishing.dto.UserInfoDTO;
import com.belen.phishing.model.UserEntity;
import com.belen.phishing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        UserEntity user = userOptional.get();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNombre(user.getNombre());
        userDTO.setApellido1(user.getApellido1());
        userDTO.setApellido2(user.getApellido2());
        userDTO.setUsername(user.getUsername());
        userDTO.setMail(user.getMail());
        userDTO.setTelefono(user.getTelefono());
        userDTO.setProfileImg(user.getProfileImg());
        userDTO.setPuntos(user.getPuntos());
        userDTO.setLNotificable(user.isLNotificable());
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/profile/{username}")
    public ResponseEntity<?> updateUserProfile(@PathVariable String username, @RequestBody UserDTO userDTO) {
        Optional<UserEntity> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserEntity user = userOptional.get();
        user.setNombre(userDTO.getNombre());
        user.setApellido1(userDTO.getApellido1());
        user.setApellido2(userDTO.getApellido2());
        user.setMail(userDTO.getMail());
        user.setTelefono(userDTO.getTelefono());
        user.setProfileImg(userDTO.getProfileImg());
        user.setLNotificable(userDTO.getLNotificable());
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profileData/{username}")
    public ResponseEntity<?> getDataUserProfile(@PathVariable String username) {
        Optional<UserEntity> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserEntity user = userOptional.get();
        UserInfoDTO userDTO = new UserInfoDTO();
        userDTO.setNombre(user.getNombre());
        userDTO.setPuntos(user.getPuntos());
        userDTO.setEntrado(user.getNPhishingEntrado());
        userDTO.setRecibido(user.getNPhishingRecibido());
        userDTO.setEnviado(user.getNPhishingPicado());

        return ResponseEntity.ok(userDTO);
    }


}