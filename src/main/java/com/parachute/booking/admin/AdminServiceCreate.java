package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceCreate {

    private final AdminRepository adminRepository;
    private final AdminDataValidate adminDataValidate;

    private final AdminMapper adminMapper;

    Admin createNewAdmin(AdminDto adminDto) {

        adminDataValidate.validateData(adminDto);

        //TODO check if exists login/mail is not doubled - password is ok

        Admin admin = new Admin();

        admin.setPassword(adminDto.getPassword());
        admin.setLogin(adminDto.getLogin());
        admin.setEmail(adminDto.getEmail());

        return adminRepository.save(admin);
    }
}
