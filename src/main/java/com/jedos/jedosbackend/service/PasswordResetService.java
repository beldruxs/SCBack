package com.jedos.jedosbackend.service;

import com.jedos.jedosbackend.model.PasswordResetToken;
import com.jedos.jedosbackend.model.UserEntity;
import com.jedos.jedosbackend.repository.PasswordResetTokenRepository;
import com.jedos.jedosbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public void generateToken(Integer userId) {
        long tokenCount = tokenRepository.countByUserIdAndCreatedAtAfter(userId, LocalDateTime.now().minusDays(1));
        if (tokenCount >= 5) {
            throw new RuntimeException("Token generation limit exceeded");
        }

        String token = String.format("%08d", new Random().nextInt(100000000));
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUserId(userId);
        resetToken.setToken(token);
        resetToken.setCreatedAt(LocalDateTime.now());
        tokenRepository.save(resetToken);

        // Send email with the token
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        String email = user.getMail();
        String subject = "Password Reset Request";
        String text = "Your password reset code is: " + token;
        emailService.sendEmail(email, subject, text);
    }

    public boolean validateToken(String token) {
        Optional<PasswordResetToken> resetToken = tokenRepository.findByToken(token);
        return resetToken.isPresent() && resetToken.get().getCreatedAt().isAfter(LocalDateTime.now().minusHours(2));
    }

    public void resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> resetToken = tokenRepository.findByToken(token);
        if (!resetToken.isPresent() || resetToken.get().getCreatedAt().isBefore(LocalDateTime.now().minusHours(2))) {
            throw new RuntimeException("Invalid or expired token");
        }

        UserEntity user = userRepository.findById(resetToken.get().getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}