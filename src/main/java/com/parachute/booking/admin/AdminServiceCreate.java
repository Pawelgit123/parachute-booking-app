package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceCreate {

    private final AdminRepository adminRepository;
    private final AdminDataValidate adminDataValidate;

    public Admin createNewAdmin(AdminDto adminDto) {

        adminDataValidate.validateData(adminDto);
        // check if exists login/mail pass is ok

        Admin admin = new Admin();

        admin.setPassword(adminDto.getPassword());
        admin.setLogin(adminDto.getLogin());
        admin.setEmail(adminDto.getEmail());

        return adminRepository.save(admin);
    }
}
