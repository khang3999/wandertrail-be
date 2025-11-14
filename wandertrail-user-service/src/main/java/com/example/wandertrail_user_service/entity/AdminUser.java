package com.example.wandertrail_user_service.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminUser extends User {
    @Override
    public String getRole() {
        return "ADMIN";
    }
}
