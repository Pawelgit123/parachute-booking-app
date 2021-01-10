package com.parachute.booking.admin;

import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDto mapAdminObjectToDto (Admin admin){
        return AdminDto.builder()
                .email(admin.getEmail())
                .login(admin.getLogin())
                .password(admin.getPassword())
                .id(admin.getId())
                .build();
    }
}
