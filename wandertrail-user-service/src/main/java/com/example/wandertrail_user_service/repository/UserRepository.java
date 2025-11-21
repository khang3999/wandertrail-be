package com.example.wandertrail_user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wandertrail_user_service.entity.User;

public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom{
    // Kiểm tra xem email đã tồn tại chưa
    boolean existsByEmail(String email);
}
