package com.example.authbackend.service;

import com.example.authbackend.model.User;
import com.example.authbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register user
    public String register(User user) {

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // Login user
    public String login(String email, String password) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return "Login Successful";
        }

        return "Invalid Credentials";
    }

    // Get user profile
    public User getUser(String email) {

        System.out.println("Searching for email: " + email);

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            System.out.println("User found");
            return user.get();
        }

        System.out.println("User NOT found");

        return null;
    }

    // Update only BIO
    public User updateProfile(String email, User updatedUser) {

        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {

            User user = existingUser.get();

            // Only update bio
            user.setBio(updatedUser.getBio());

            userRepository.save(user);

            return user;
        }

        return null;
    }
}