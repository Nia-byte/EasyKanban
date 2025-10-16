/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.easykanban.service;

import com.example.easykanban.entity.User;
import com.example.easykanban.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Pattern;
/**
 *
 * @author lab_services_student
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User registerUser(User user) { //Validates and saves user
        if (!isValidUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username must contain underscore and be max 5 characters");
        }
        
        if (!isValidPassword(user.getPassword())) {
            throw new IllegalArgumentException("Password must be at least 8 characters with uppercase, number, and special character");
        }
        
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        
        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return userRepository.save(user);
    }
    
    public Optional<User> loginUser(String username, String password) { //Authenticates user
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
    
    private boolean isValidUsername(String username) { //Checks username has underscore and is max 5 characters
        return username.contains("_") && username.length() <= 5;
    }
    
    private boolean isValidPassword(String password) { //Checks password complexity
        return password.length() >= 8 &&
               Pattern.compile("[A-Z]").matcher(password).find() &&
               Pattern.compile("[0-9]").matcher(password).find() &&
               Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }
}
