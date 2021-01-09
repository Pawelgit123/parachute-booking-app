package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/admins")
public class AdminController {

    private final AdminServiceCreate adminServiceCreate;
    private final AdminServiceSearch adminServiceSearch;
    private final AdminServiceRemove adminServiceRemove;
    private final AdminMapper adminMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdminDto createNewAdmin(@Valid @RequestBody AdminDto adminDto) {

        Admin newAdmin = adminServiceCreate.createNewAdmin(adminDto);

        return adminMapper.mapAdminObjectToDto(newAdmin);
    }

    @GetMapping
    public Set<AdminDto> getAdminSet() {

        return adminServiceSearch.getAllAdmins()
                .stream()
                .map(adminMapper::mapAdminObjectToDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public AdminDto getAdminById(@PathVariable Long id) {

        Admin admin = adminServiceSearch.findById(id);
        return adminMapper.mapAdminObjectToDto(admin);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdminById(@PathVariable Long id) {

        adminServiceRemove.adminDelete(id);
    }

//    @GetMapping("/{login}")
//    public AdminDto getAdminByLogin(@PathVariable String login) {
//
//        Admin admin = adminServiceSearch.findByLogin(login);
//        return adminMapper.mapAdminObjectToDto(admin);
//    }
//
//    @GetMapping("/{email}")
//    public AdminDto getAdminByEmail(@PathVariable String email) {
//
//        Admin admin = adminServiceSearch.findByEmail(email);
//        return adminMapper.mapAdminObjectToDto(admin);
//
//    }

}
