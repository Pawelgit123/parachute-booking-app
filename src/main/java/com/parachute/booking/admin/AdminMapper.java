package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminMapper {

    private final PasswordEncoder passwordEncoder;

    public AdminDto mapAdminDto(Admin admin) {
        return AdminDto.builder()
                .email(admin.getEmail())
                .login(admin.getLogin())
                .id(admin.getId())
                .build();
    }

    public Admin mapAdmin(AdminDto adminDto) {
        return Admin.builder()
                .email(adminDto.getEmail())
                .login(adminDto.getLogin())
                .password(passwordEncoder.encode(adminDto.getPassword()))
                .build();
    }
}
