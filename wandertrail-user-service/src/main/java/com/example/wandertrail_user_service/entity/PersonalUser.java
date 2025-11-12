package com.example.wandertrail_user_service.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PERSONAL")
public class PersonalUser extends User {
    @Override
    public String getRole() {
        return "PERSONAL";
    }
}
