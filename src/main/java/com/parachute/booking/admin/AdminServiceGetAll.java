package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceGetAll {

    private final AdminRepository adminRepository;

    List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
