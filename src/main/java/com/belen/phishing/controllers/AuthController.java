package com.belen.phishing.controllers;

import com.belen.phishing.dto.*;
import com.belen.phishing.model.UserEntity;
import com.belen.phishing.repository.RoleRepository;
import com.belen.phishing.repository.UserRepository;
import com.belen.phishing.security.JWTGenerator;
import com.belen.phishing.service.AuthService;
import com.belen.phishing.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Deshabilita las CORS para todos los métodos en este controlador
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    private EmailService emailService;

    @Autowired
    private AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        // Obtener el ID del usuario
        Optional<Integer> userOptional = userRepository.findIdByUsername(loginDto.getUsername());
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found after successful authentication");
        }

        String username = loginDto.getUsername();

        return new ResponseEntity<>(new AuthResponseDTO(token, username), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("loginAdmin")
    public ResponseEntity<AuthResponseDTO> loginAdmin(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Obtener el usuario autenticado
        Optional<UserEntity> userOptional = userRepository.findUserdByUsername(loginDto.getUsername());
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found after successful authentication");
        }

        UserEntity user = userOptional.get();

        // Verificar si el usuario tiene el rol de admin (role_id = 1)
        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getId() == 1);

        if (!isAdmin) {
            return new ResponseEntity<>(new AuthResponseDTO(null, "Access denied. Admin role required."), HttpStatus.FORBIDDEN);
        }

        String token = jwtGenerator.generateToken(authentication);
        String username = user.getUsername();

        return new ResponseEntity<>(new AuthResponseDTO(token, username), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
}