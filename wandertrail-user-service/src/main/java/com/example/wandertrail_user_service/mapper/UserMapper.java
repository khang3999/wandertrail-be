package com.example.wandertrail_user_service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.wandertrail_user_service.dto.request.UserRegisterDTO;
import com.example.wandertrail_user_service.dto.response.UserRegisterResponseDTO;
import com.example.wandertrail_user_service.entity.BusinessUser;
import com.example.wandertrail_user_service.entity.PersonalUser;
import com.example.wandertrail_user_service.entity.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserRegisterResponseDTO toUserRegisterResponse(User user) {
        UserRegisterResponseDTO userResponse = new UserRegisterResponseDTO();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setAvatarUrl(user.getAvatarUrl());
        userResponse.setRole(user.getRole());
        return userResponse;
    }

    // Methods
    public User createUserByRole(UserRegisterDTO userRegisterDTO) {
        User user = (userRegisterDTO.getRole() == User.Role.PERSONAL) ? new PersonalUser() : new BusinessUser();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setEmail(userRegisterDTO.getEmail());
        return user;
    }
}
