package com.example.proj1.controllers;

import com.example.proj1.models.User;
import com.example.proj1.repositories.UserRepository;
import com.example.proj1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // Login user
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());

        if (foundUser != null && passwordMatches(user.getPassword(), foundUser.getPassword())) {
            return ResponseEntity.ok("Login successful"); // Successful login
        }

        return ResponseEntity.status(401).body("Invalid username or password"); // Unauthorized
    }

    private boolean passwordMatches(String rawPassword, String encodedPassword) {
        // Use your preferred password encoding/verification method
        // Here we assume you use BCrypt
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
}
