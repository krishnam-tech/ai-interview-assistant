package com.krishna.ai_interview_assistant.controller;

import com.krishna.ai_interview_assistant.entity.User;
import com.krishna.ai_interview_assistant.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")   // ✅ allow frontend requests
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // ✅ Register API
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    // ✅ Login API
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        // 🔍 DEBUG INPUT
        System.out.println("INPUT USERNAME: " + user.getUsername());
        System.out.println("INPUT PASSWORD: " + user.getPassword());

        Optional<User> existingUser =
                userRepository.findByUsername(user.getUsername());

        if (existingUser.isPresent()) {

            User dbUser = existingUser.get();

            // 🔍 DEBUG DATABASE VALUES
            System.out.println("DB USERNAME: " + dbUser.getUsername());
            System.out.println("DB PASSWORD: " + dbUser.getPassword());

            if (dbUser.getPassword().equals(user.getPassword())) {
                System.out.println("LOGIN SUCCESS");
                return "Login Successful";
            } else {
                System.out.println("PASSWORD MISMATCH");
            }

        } else {
            System.out.println("USER NOT FOUND IN DATABASE");
        }

        return "Invalid Credentials";
    }
}