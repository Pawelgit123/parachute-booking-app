package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceCreate {

    private final AdminRepository adminRepository;
}
