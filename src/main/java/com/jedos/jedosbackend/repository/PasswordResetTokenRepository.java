package com.jedos.jedosbackend.repository;

import com.jedos.jedosbackend.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    long countByUserIdAndCreatedAtAfter(Integer userId, LocalDateTime dateTime);
}