package com.example.wandertrail_user_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wandertrail_user_service.dto.request.UserRegisterDTO;
import com.example.wandertrail_user_service.dto.response.UserRegisterResponseDTO;
import com.example.wandertrail_user_service.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // Dùng field final phải thêm
@RequestMapping("api/v1")
// @CrossOrigin(origins = "http://localhost:3000") // Chỉ dùng cho phát triển
// front-end local
public class UserController {
    @Autowired
    private final UserService userService;

    // public UserController(UserService userService) {
    // this.userService = userService;
    // }
    @GetMapping("/auth/test")
    public Map<String, String> test() {
        return Map.of("message", "Test endpoint is working");
    }

    // @GetMapping
    // public List<User> getAllUsers() {
    // return userService.getUsers();
    // }

    @PostMapping("/auth/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        // Call service: exceptions are handled globally
        UserRegisterResponseDTO userRegisterResponse = userService.saveUser(userRegisterDTO);
        Map<String, Object> response = Map.of(
                "status", 200,
                "message", "User registered successfully",
                "data", userRegisterResponse);

        return ResponseEntity.ok(response);
    }

    // @GetMapping("/{id}")
    // public User getUserById(@PathVariable String id) {
    // return userService.getUserById(id);
    // }

    // @PostMapping
    // public User createUser(@RequestBody User user) {
    // return userService.createUser(user);
    // }

    // @PutMapping("/{id}")
    // public User updateUser(@PathVariable String id, @RequestBody User user) {
    // return userService.updateUser(id, user);
    // }

    // @DeleteMapping("/{id}")
    // public void deleteUser(@PathVariable String id) {
    // userService.deleteUser(id);
    // }
}
