package com.example.wandertrail_user_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wandertrail_user_service.dto.request.UserRegisterDTO;
import com.example.wandertrail_user_service.dto.response.UserRegisterResponseDTO;
import com.example.wandertrail_user_service.entity.User;
import com.example.wandertrail_user_service.exception.ValidationException;
import com.example.wandertrail_user_service.mapper.UserMapper;
import com.example.wandertrail_user_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    // @Autowired
    // private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        // Implement the logic to retrieve all users
        return null;
    }

    @Override
    public UserRegisterResponseDTO saveUser(UserRegisterDTO userRegisterDTO) {
        // Kiểm tra mail tồn tại và confirmPassword
        validateData(userRegisterDTO);
        // // Kiểm tra email tồn tại
        // if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
        //     throw new IllegalArgumentException("Email already exists");
        // }

        // // Kiểm tra role
        // User.Role role = userRegisterDTO.getRole();
        // if (role == null) {
        //     throw new IllegalArgumentException("Unknown role");
        // }
        User newUser = userMapper.createUserByRole(userRegisterDTO);
        User savedUser = userRepository.save(newUser);
        UserRegisterResponseDTO userResponse = userMapper.toUserRegisterResponse(savedUser);
        return userResponse;
    }

    private Map<String, String> validateData(UserRegisterDTO userRegisterDTO) {
        Map<String, String> fieldErrors = new HashMap<>();

        // Check email duplicate
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            fieldErrors.put("email", "Email already exists");
        }

        // Check confirm password
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            fieldErrors.put("confirmPassword", "Passwords do not match");
        }

        User.Role role = userRegisterDTO.getRole();
        if (role == null) {
            fieldErrors.put("role", "Unknown role");
        }

        if (!fieldErrors.isEmpty()) {
            throw new ValidationException(fieldErrors);
        }
        return fieldErrors;
    }

}
