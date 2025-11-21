package com.example.wandertrail_user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("BUSINESS")
@Getter
@Setter
public class BusinessUser extends User {
    @Column(name = "business_license_id")
    private String businessLicenseId;
    @Override
    public User.Role getRole() {
        return User.Role.BUSINESS;
    }
}
