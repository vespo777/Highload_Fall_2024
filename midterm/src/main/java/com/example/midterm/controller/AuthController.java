//package com.example.midterm.controller;
//
//import com.example.midterm.util.JwtUtil;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class AuthController {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/login")
//    public String login(@RequestBody AuthRequest authRequest) throws Exception {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//        );
//        return jwtUtil.generateToken(authRequest.getUsername());
//    }
//}
//
//@Data
//class AuthRequest {
//    private String username;
//    private String password;
//
//    // getters and setters
//}
//
