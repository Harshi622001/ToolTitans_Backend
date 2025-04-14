package com.mobileApp.mobileApp.serviceImpl;

import com.mobileApp.mobileApp.DTO.LoginDTO;
import com.mobileApp.mobileApp.DTO.RegisterDTO;
import com.mobileApp.mobileApp.entity.UserEntity;
import com.mobileApp.mobileApp.repository.UserRepo;
import com.mobileApp.mobileApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register user

    @Override
    public UserEntity registerUser(@Valid RegisterDTO registerDTO) {
        if (registerDTO.getMobileNumber() != null && registerDTO.getMobileNumber().toString().length() != 10) {
            throw new IllegalArgumentException("Mobile number must be 10 digits");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword())); // Encrypt password before saving
        user.setMobileNumber(registerDTO.getMobileNumber());
        return userRepository.save(user);
    }

    // Login user
    @Override
    public UserEntity loginUser(LoginDTO loginDTO) throws Exception {
        Optional<UserEntity> userOpt = userRepository.findByEmail(loginDTO.getEmail());
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            // Compare the hashed password stored in the database with the provided password
            if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                return user;
            } else {
                throw new Exception("Invalid credentials");
            }
        } else {
            throw new Exception("User not found please signUp");
        }
    }

    public String forgotPassword(String email, String newPassword) throws Exception {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            // Hash the new password before updating
            String hashedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(hashedPassword);
            userRepository.save(user);
            return "Password successfully reset";
        } else {
            throw new Exception("User with this email not found");
        }
    }
}