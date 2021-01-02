package com.parachute.booking.admin;

import com.parachute.booking.ExceptionBadData;
import com.parachute.booking.ExceptionBlankSpaces;
import com.parachute.booking.ExceptionNoData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceCreate {

    private final AdminRepository adminRepository;
    private final AdminDataValidate adminDataValidate;

    Admin createNewAdmin(AdminDto adminDto) {

        adminDataValidate.validateData(adminDto);

        Admin admin = new Admin();

        admin.setPassword(adminDto.getPassword());
        admin.setLogin(adminDto.getLogin());
        admin.setEmail(adminDto.getEmail());

        return adminRepository.save(admin);
    }
}
