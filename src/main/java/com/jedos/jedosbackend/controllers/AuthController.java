package com.jedos.jedosbackend.controllers;

import com.jedos.jedosbackend.dto.*;
import com.jedos.jedosbackend.model.Role;
import com.jedos.jedosbackend.model.UserEntity;
import com.jedos.jedosbackend.repository.RoleRepository;
import com.jedos.jedosbackend.repository.UserRepository;
import com.jedos.jedosbackend.security.JWTGenerator;
import com.jedos.jedosbackend.service.PasswordResetService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private JavaMailSender mailSender;

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

        Integer idUser = userOptional.get();

        return new ResponseEntity<>(new AuthResponseDTO(token, idUser), HttpStatus.OK);
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

    @CrossOrigin(origins = "*")
    @GetMapping("is_admin")
    public ResponseEntity<Boolean> isAdmin(@RequestHeader("Authorization") String token) {
        String username = jwtGenerator.getUsernameFromToken(token.replace("Bearer ", ""));
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Role adminRole = roleRepository.findByName("ADMIN").orElseThrow(() -> new RuntimeException("Role not found"));
        boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.equals(adminRole));
        return new ResponseEntity<>(isAdmin, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("login/admin")
    public ResponseEntity<?> loginAsAdmin(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        // Obtener el ID del usuario
        Optional<Integer> userOptional = userRepository.findIdByUsername(loginDto.getUsername());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found after successful authentication");
        }

        Integer idUser = userOptional.get();
        UserEntity user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
        Role adminRole = roleRepository.findByName("ADMIN").orElseThrow(() -> new RuntimeException("Role not found"));
        boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.equals(adminRole));

        if (isAdmin) {
            return new ResponseEntity<>(new AuthResponseDTO(token, idUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("login/google")
    public ResponseEntity<AuthResponseDTO> googleLogin(OAuth2AuthenticationToken authentication) {
        OAuth2User oAuth2User = authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        UserEntity user = userRepository.findByMail(email).orElseGet(() -> {
            UserEntity newUser = new UserEntity();
            newUser.setUsername(email);
            newUser.setMail(email);
            newUser.setNombre(oAuth2User.getAttribute("given_name"));
            newUser.setApellido(oAuth2User.getAttribute("family_name"));
            newUser.setProfileImg(oAuth2User.getAttribute("picture"));
            Role roles = roleRepository.findByName("USER").get();
            newUser.setRoles(Collections.singletonList(roles));
            userRepository.save(newUser);
            return newUser;
        });

        Integer userId = userRepository.findIdByMail(email).orElseThrow(() -> new RuntimeException("User not found after saving"));

        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtGenerator.generateToken(auth);

        return new ResponseEntity<>(new AuthResponseDTO(token, userId), HttpStatus.OK);
    }

    @PostMapping("/request-password-reset")
    public ResponseEntity<String> requestPasswordReset(@RequestBody PasswordResetRequestDto requestDto) {
        Optional<UserEntity> userOptional = userRepository.findByMail(requestDto.getMail());
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        UserEntity user = userOptional.get();
        String token = passwordResetService.generateToken(user.getId());

        // Send email
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getMail());
            helper.setSubject("Password Reset Request");
            helper.setText("Your password reset code is: " + token);
            mailSender.send(message);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Password reset token sent", HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetDto resetDto) {
        if (!passwordResetService.validateToken(resetDto.getToken())) {
            return new ResponseEntity<>("Invalid or expired token", HttpStatus.BAD_REQUEST);
        }

        passwordResetService.resetPassword(resetDto.getToken(), resetDto.getNewPassword());
        return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
    }

}