package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final AdminServiceCreate adminServiceCreate;
    private final AdminServiceSearch adminServiceSearch;
    private final AdminServiceRemove adminServiceRemove;
    private final AdminMapper adminMapper;
    private final AdminDataValidate adminDataValidate;

    @PostMapping("/admin")
    ResponseEntity<AdminDto> createNewAdmin(AdminDto adminDto) {

        Admin newAdmin = adminServiceCreate.createNewAdmin(adminDto, adminDataValidate);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adminMapper.mapAdminObjectToDto(newAdmin));
    }

    @GetMapping("/admin")
    Set<AdminDto> getAdminSet() {

        return adminServiceSearch.getAllAdmins()
                .stream()
                .map(adminMapper::mapAdminObjectToDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/admin/{id}")
    AdminDto getAdminById(@PathVariable Long id) {

        Admin admin = adminServiceSearch.findById(id);
        return adminMapper.mapAdminObjectToDto(admin);
    }

    @DeleteMapping("/admin/{id}")
    AdminDto deleteAdminById(@PathVariable Long id) {

        Admin admin = adminServiceSearch.findById(id);
        AdminDto adminDto = adminMapper.mapAdminObjectToDto(admin);
        adminServiceRemove.adminDelete(id);
        return adminDto;
    }
}
