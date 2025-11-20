package com.example.wandertrail_user_service.service;

import java.util.List;

import com.example.wandertrail_user_service.dto.UserRegisterDTO;
import com.example.wandertrail_user_service.entity.User;

public interface  UserService {
    List<User> getUsers();
    void saveUser(UserRegisterDTO userRegisterDTO);
}
