/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.easykanban.controller;

import com.example.easykanban.entity.User;
import com.example.easykanban.security.JwtTokenProvider;
import com.example.easykanban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.Optional;
/**
 *
 * @author lab_services_student
 */
@RestController //Marks this class as a REST API controller
@RequestMapping("/api/users") //Sets the base URL path for all endpoints in this controller
@CrossOrigin(origins = "*") //Allows requests from any file
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) { //Converts JSON from request body to User object
       try {
            User registeredUser = userService.registerUser(user);
            String token = tokenProvider.generateToken(registeredUser.getUsername(), registeredUser.getId());
            
            return ResponseEntity.ok(Map.of(
                "message", "User registered successfully",
                "token", token,
                "user", Map.of(
                    "id", registeredUser.getId(),
                    "username", registeredUser.getUsername(),
                    "firstName", registeredUser.getFirstName(),
                    "lastName", registeredUser.getLastName()
                )
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
       String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        
        Optional<User> user = userService.loginUser(username, password);
        if (user.isPresent()) {
            String token = tokenProvider.generateToken(user.get().getUsername(), user.get().getId());
            
            return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "token", token,
                "user", Map.of(
                    "id", user.get().getId(),
                    "username", user.get().getUsername(),
                    "firstName", user.get().getFirstName(),
                    "lastName", user.get().getLastName()
                )
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Username or password incorrect"
            ));
        }
    }
}
