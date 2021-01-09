package com.parachute.booking.admin;

import com.parachute.booking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceSearch {

    private final AdminRepository adminRepository;

    // List<AdminDto>
    List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    Admin findById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Admin with ID: " + id));
    }

    Admin findByLogin(String login) {
        return adminRepository.findAdminByLogin(login);
    }

    Admin findByEmail(String email) {
        return adminRepository.findAdminByEmail(email);
    }
}
