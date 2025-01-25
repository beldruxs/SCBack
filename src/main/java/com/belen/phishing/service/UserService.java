package com.belen.phishing.service;

import com.belen.phishing.model.UserEntity;
import com.belen.phishing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(UserEntity user) {
        userRepository.save(user);
    }
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}