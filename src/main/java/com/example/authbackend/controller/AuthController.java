package com.example.authbackend.controller;

import com.example.authbackend.model.User;
import com.example.authbackend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // allow Next.js frontend
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    // Register user
    @PostMapping("/register")
    public String register(@RequestBody User user){
        return authService.register(user);
    }

    // Login user
    @PostMapping("/login")
    public String login(@RequestBody User user){
        return authService.login(user.getEmail(), user.getPassword());
    }

    // Get user profile by email
    @GetMapping("/profile/{email}")
    public User getProfile(@PathVariable String email){
        return authService.getUser(email);
    }

    // Update user bio only
    @PutMapping("/profile/{email}")
    public User updateProfile(@PathVariable String email, @RequestBody User user){
        return authService.updateProfile(email, user);
    }
}