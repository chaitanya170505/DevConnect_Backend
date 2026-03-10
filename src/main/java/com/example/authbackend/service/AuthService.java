package com.example.authbackend.service;

import com.example.authbackend.model.User;
import com.example.authbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String register(User user){

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public String login(String email,String password){

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent() && user.get().getPassword().equals(password)){
            return "Login Successful";
        }

        return "Invalid Credentials";
    }

}