package com.parachute.booking.admin;

import com.parachute.booking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceSearch {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminDtoListed getAllAdmins() {

        List<Admin> all = adminRepository.findAll();
        AdminDtoListed adminDtoListed = new AdminDtoListed();

        Set<AdminDto> collect = all.stream().map(adminMapper::mapAdminDto).collect(Collectors.toSet());
        adminDtoListed.setAdmins(collect);

        return adminDtoListed;
    }

    public AdminDto findById(Long id) {

        Optional<Admin> byId = adminRepository.findById(id);

        return adminMapper.mapAdminDto(byId
                .orElseThrow(() -> new NotFoundException("Not found Admin with ID: " + id)));
    }

    public AdminDto findByLogin(String login) {

        Optional<Admin> adminByLogin = adminRepository.findAdminByLogin(login);

        return adminMapper.mapAdminDto(adminByLogin
                .orElseThrow(() -> new NotFoundException("Not found Admin with Login: " + login)));
    }

    public AdminDto findByEmail(String email) {

        Optional<Admin> adminByEmail = adminRepository.findAdminByEmail(email);

        return adminMapper.mapAdminDto(adminByEmail
                .orElseThrow(() -> new NotFoundException("Not found Admin with Email: " + email)));
    }

}
