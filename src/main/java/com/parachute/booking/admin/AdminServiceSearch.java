package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceSearch {

    private final AdminRepository adminRepository;

    List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    Admin fingById(Long id){
        return adminRepository.findById(id)
                .orElseThrow();
    }
}
