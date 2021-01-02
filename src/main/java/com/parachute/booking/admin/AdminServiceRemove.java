package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceRemove {

    private final AdminRepository adminRepository;

    void adminDelete(Long id){
        adminRepository.deleteById(id);
    }
}
