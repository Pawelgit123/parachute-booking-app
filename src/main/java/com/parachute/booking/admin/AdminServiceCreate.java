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
    private final AdminMapper adminMapper;

    public AdminDto createNewAdmin(AdminDto adminDto) {
        // adminDto -> Admin
        // Admin -> save -> Admin with generated fields
        // Admin with generated fields -> AdminDto

        adminDataValidate.validateData(adminDto);

        //TODO check if exists login/mail is not doubled - password is ok

        Admin admin = new Admin();

        admin.setPassword(adminDto.getPassword());
        admin.setLogin(adminDto.getLogin());
        admin.setEmail(adminDto.getEmail());

        final Admin savedAdmin = adminRepository.save(admin);
        
        return adminMapper.mapAdminObjectToDto(savedAdmin);
    }
}