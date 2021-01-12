package com.parachute.booking.admin;

import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDto mapAdminDto(Admin admin){
        return AdminDto.builder()
                .email(admin.getEmail())
                .login(admin.getLogin())
                .password(admin.getPassword())
                .id(admin.getId())
                .build();
    }

    public Admin mapAdmin(AdminDto adminDto){
        return Admin.builder()
                .email(adminDto.getEmail())
                .login(adminDto.getLogin())
                .password(adminDto.getPassword())
                .build();
    }
}
