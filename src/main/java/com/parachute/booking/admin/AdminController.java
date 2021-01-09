package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController // @Controller + @ResponseBody
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

    //web DTO
    //serwis DTO, Encjach
    //db - encje

    @GetMapping()
    Admins getAdminSet() {

        return new Admins(adminServiceSearch.getAllAdmins()
                .stream()
                .map(adminMapper::mapAdminObjectToDto)
                .collect(Collectors.toSet()));
    }
    /*
     {
        pole: wartosc
     }

     [
        {obj1},
        {obj2},
        {obj3}
     ]

     */

    @GetMapping("/{id}") // /admins/{id}
    AdminDto getAdminById(@PathVariable Long id) {

        Admin admin = adminServiceSearch.findById(id);
        return adminMapper.mapAdminObjectToDto(admin);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteAdminById(@PathVariable Long id) {

        Admin admin = adminServiceSearch.findById(id);
        AdminDto adminDto = adminMapper.mapAdminObjectToDto(admin);
        adminServiceRemove.adminDelete(id);
    }
//    @GetMapping("/admin/{login}")
//    AdminDto getAdminByLogin(@PathVariable String login) {
//
//        Admin admin = adminServiceSearch.findByLogin(login);
//        return adminMapper.mapAdminObjectToDto(admin);
//    }
//
//    @GetMapping("/admin/{email}")
//    AdminDto getAdminByEmail(@PathVariable String email) {
//
//        Admin admin = adminServiceSearch.findByEmail(email);
//        return adminMapper.mapAdminObjectToDto(admin);
//
//    }

}
