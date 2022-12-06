package com.example.avochadbackend.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.graphql.data.method.annotation.Argument;

import com.example.avochadbackend.dto.UserLoginDTO;
import com.example.avochadbackend.security.JWTUtil;
import com.example.avochadbackend.services.UserService;


@RestController("/auth")
public class AuthController {
    
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private String jwtToken = "";

    @Autowired
    public AuthController(UserService userService, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return ResponseEntity.ok("Hello World");
    }

    public ResponseEntity<String> register() {
        return ResponseEntity.ok("Hello World");
    }
}
