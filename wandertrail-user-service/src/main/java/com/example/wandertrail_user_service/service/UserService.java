package com.example.wandertrail_user_service.service;

import java.util.List;

import com.example.wandertrail_user_service.dto.request.UserRegisterDTO;
import com.example.wandertrail_user_service.dto.response.UserRegisterResponseDTO;
import com.example.wandertrail_user_service.entity.User;

public interface  UserService {
    public List<User> getUsers();
    public UserRegisterResponseDTO saveUser(UserRegisterDTO userRegisterDTO);
}
