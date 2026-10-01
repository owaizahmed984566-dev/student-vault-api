package com.owaizz.studentapi.controller;

import com.owaizz.studentapi.dto.LoginRequest;
import com.owaizz.studentapi.dto.RegisterRequest;
import com.owaizz.studentapi.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {

        service.register(request);

        return ResponseEntity
                .status(201)
                .body("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest request) {

        String token = service.login(
                request.getUsername(),
                request.getPassword()
        );

        return ResponseEntity.ok(token);
    }
}