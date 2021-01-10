package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import java.util.List;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdminDto createNewAdmin(@Valid @RequestBody AdminDto adminDto) {

        return adminServiceCreate.createNewAdmin(adminDto);
    }

    @GetMapping
    public Set<AdminDto> getAdmins() {

        return adminServiceSearch.getAllAdmins();
    }

    @GetMapping("/{id}")
    public AdminDto getAdminById(@PathVariable Long id) {

        return adminServiceSearch.findById(id);
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
