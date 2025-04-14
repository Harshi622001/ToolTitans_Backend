package com.mobileApp.mobileApp.controller;

import com.mobileApp.mobileApp.DTO.LoginDTO;
import com.mobileApp.mobileApp.DTO.RegisterDTO;
import com.mobileApp.mobileApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    // Register endpoint
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody RegisterDTO registerDTO) {
        try {
            userService.registerUser(registerDTO);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error registering user: " + e.getMessage());
        }
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            userService.loginUser(loginDTO);
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials: " + e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email, @RequestParam String newPassword) {
        try {
            String message = userService.forgotPassword(email, newPassword);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

        @PostMapping("/logout")
        public ResponseEntity<String> logout(HttpServletRequest request) {
            // Invalidate the current session
            request.getSession().invalidate();

            return ResponseEntity.ok("You have been logged out successfully.");
        }
    }


