package com.example.wandertrail_user_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wandertrail_user_service.dto.UserRegisterDTO;
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
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO,
            BindingResult bindingResult) {
        // Double validate: BE tier có thể dùng ControllerAdvice
        // Print toàn bộ DTO
        System.out.println("=== Incoming UserRegisterDTO ===");
        System.out.println(userRegisterDTO);

        if (bindingResult.hasErrors()) {
            Map<String, String> fieldErrors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> {
                fieldErrors.put(err.getField(), err.getDefaultMessage());
            });
            Map<String, Object> response = Map.of(
                    "status", 400,
                    "message", "Validation failed",
                    "fieldErrors", fieldErrors);
            return ResponseEntity.badRequest().body(response);
        }
        // Check confirmPassword
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            Map<String, Object> response = Map.of(
                    "status", 400,
                    "message", "Validation failed",
                    "fieldErrors", Map.of(
                            "confirmPassword", "Passwords do not match"));
            return ResponseEntity.badRequest().body(response);
        }
        // Call service
        int data = 10;
        Map<String, Object> response = Map.of(
                "status", 200,
                "message", "User registered successfully",
                "data", data);

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
