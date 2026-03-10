package com.example.authbackend.controller;

import com.example.authbackend.model.User;
import com.example.authbackend.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){

        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){

        return authService.login(user.getEmail(), user.getPassword());
    }
}