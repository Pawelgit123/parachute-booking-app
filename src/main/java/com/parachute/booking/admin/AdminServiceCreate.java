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

        adminDataValidate.validateData(adminDto);

        //TODO check if exists login/mail is not doubled - password is ok

        Admin admin = new Admin();

        admin.setPassword(adminDto.getPassword());
        admin.setLogin(adminDto.getLogin());
        admin.setEmail(adminDto.getEmail());

//        Admin admin = adminMapper.mapAdmin(adminDto);

        Admin save = adminRepository.save(admin);

        return adminMapper.mapAdminDto(save);
    }
}
