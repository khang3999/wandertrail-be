package com.example.wandertrail_user_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wandertrail_user_service.dto.UserRegisterDTO;
import com.example.wandertrail_user_service.entity.User;
import com.example.wandertrail_user_service.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
// @CrossOrigin(origins = "http://localhost:3000") // Chỉ dùng cho phát triển front-end local
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/auth/test")
    public Map<String, String> test() {
        return Map.of("message", "Test endpoint is working");
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDTO user, BindingResult bindingResult) {
        // Kiểm tra có lỗi validate không
        if (bindingResult.hasErrors()) {
            Map<String, String> fieldErrors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> {
                fieldErrors.put(err.getField(), err.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(Map.of("fieldErrors", fieldErrors));
        }
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
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
