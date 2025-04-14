package com.mobileApp.mobileApp.service;

import com.mobileApp.mobileApp.DTO.LoginDTO;
import com.mobileApp.mobileApp.DTO.RegisterDTO;
import com.mobileApp.mobileApp.entity.UserEntity;
import jakarta.validation.Valid;

public interface UserService {
    UserEntity registerUser(@Valid RegisterDTO registerDTO);
    UserEntity loginUser(LoginDTO loginDTO) throws Exception;

    String forgotPassword(String email, String newPassword) throws Exception;
}
