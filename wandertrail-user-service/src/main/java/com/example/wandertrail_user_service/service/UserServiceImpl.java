package com.example.wandertrail_user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.wandertrail_user_service.dto.UserRegisterDTO;
import com.example.wandertrail_user_service.entity.BusinessUser;
import com.example.wandertrail_user_service.entity.PersonalUser;
import com.example.wandertrail_user_service.entity.User;
import com.example.wandertrail_user_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        // Implement the logic to retrieve all users
        return null;
    }

    @Override
    public void saveUser(UserRegisterDTO userRegisterDTO) {
        User.Role role = userRegisterDTO.getRole();
        if (role == null) {
            throw new IllegalArgumentException("Unknown role");
        }
        User newUser = createUserByRole(userRegisterDTO);
        userRepository.save(newUser);
    }

    // Methods
    private User createUserByRole(UserRegisterDTO userRegisterDTO) {
        User user = (userRegisterDTO.getRole() == User.Role.PERSONAL) ? new PersonalUser() : new BusinessUser();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setEmail(userRegisterDTO.getEmail());
        return user;
    }

}
