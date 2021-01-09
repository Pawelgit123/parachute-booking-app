package com.parachute.booking.admin;

import com.parachute.booking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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

    public Set<AdminDto> getAllAdmins() {

        List<Admin> all = adminRepository.findAll();
        new HashSet<>(all);

        return all.stream()
                .map(adminMapper::mapAdminObjectToDto)
                .collect(Collectors.toSet());
    }

    public AdminDto findById(Long id) {

        Optional<Admin> byId = adminRepository.findById(id);

        return adminMapper.mapAdminObjectToDto(byId
                .orElseThrow(() -> new NotFoundException("Not found Admin with ID: " + id)));
    }

//    public Admin findByLogin(String login) {
//        return adminRepository.findAdminByLogin(login);
//    }
//
//    public Admin findByEmail(String email) {
//        return adminRepository.findAdminByEmail(email);
//    }

    //TODO find by login and find by email
}
