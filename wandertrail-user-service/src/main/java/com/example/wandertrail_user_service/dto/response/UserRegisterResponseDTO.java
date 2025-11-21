package com.example.wandertrail_user_service.dto.response;

import com.example.wandertrail_user_service.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponseDTO {
    private String id;
    private String username;
    private String avatarUrl;
    private User.Role role;
}
