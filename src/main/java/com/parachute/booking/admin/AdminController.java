package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    private final AdminServiceCreate adminServiceCreate;
    private final AdminServiceSearch adminServiceSearch;
    private final AdminServiceRemove adminServiceRemove;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public AdminDto createNewAdmin(@Valid @RequestBody AdminDto adminDto) {

        return adminServiceCreate.createNewAdmin(adminDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<AdminDto> getAdmins() {

        return adminServiceSearch.getAllAdmins();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdminDto getAdminById(@PathVariable Long id) {

        return adminServiceSearch.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdminById(@PathVariable Long id) {

        adminServiceRemove.adminDelete(id);
    }

    @GetMapping("/login/{login}")
    @ResponseStatus(HttpStatus.OK)
    public AdminDto getAdminByLogin(@PathVariable String login) {

        return adminServiceSearch.findByLogin(login);
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public AdminDto getAdminByEmail(@PathVariable String email) {

        return adminServiceSearch.findByEmail(email);
    }

}
