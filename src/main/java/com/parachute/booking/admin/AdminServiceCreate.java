package com.parachute.booking.admin;

import com.parachute.booking.exceptions.InternalServerException;
import com.parachute.booking.security.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceCreate {

    private final AdminRepository adminRepository;
    private final AdminDataValidate adminDataValidate;

    private final AdminMapper adminMapper;

    public AdminDto createNewAdmin(AdminDto adminDto) {

        if (adminDto == null) {
            throw new InternalServerException("No data to create Admin");
        }

        adminDataValidate.validateData(adminDto);

        //TODO check if exists login/mail is not doubled - password is ok

        final Admin admin = adminMapper.mapAdmin(adminDto);
        admin.setAuthority(Collections.singletonList(Roles.ADMIN::toString));

        Admin save = adminRepository.save(admin);

        return adminMapper.mapAdminDto(save);
    }
}
