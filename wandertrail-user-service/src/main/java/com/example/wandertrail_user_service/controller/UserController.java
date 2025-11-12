package com.example.wandertrail_user_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wandertrail_user_service.entity.User;
import com.example.wandertrail_user_service.service.UserService;
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // @GetMapping("/{id}")
    // public User getUserById(@PathVariable String id) {
    //     return userService.getUserById(id);
    // }

    // @PostMapping
    // public User createUser(@RequestBody User user) {
    //     return userService.createUser(user);
    // }

    // @PutMapping("/{id}")
    // public User updateUser(@PathVariable String id, @RequestBody User user) {
    //     return userService.updateUser(id, user);
    // }

    // @DeleteMapping("/{id}")
    // public void deleteUser(@PathVariable String id) {
    //     userService.deleteUser(id);
    // }
}
