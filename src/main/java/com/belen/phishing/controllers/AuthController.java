package com.belen.phishing.controllers;

import com.belen.phishing.dto.*;
import com.belen.phishing.model.Role;
import com.belen.phishing.model.UserEntity;
import com.belen.phishing.repository.RoleRepository;
import com.belen.phishing.repository.UserRepository;
import com.belen.phishing.security.JWTGenerator;
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
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setNombre(registerDto.getNombre());
        user.setApellido(registerDto.getApellido());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setMail(registerDto.getMail());
        user.setProfileImg(registerDto.getProfileImg());

        // Asume que Role ya está definido para "USER"
        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }




}