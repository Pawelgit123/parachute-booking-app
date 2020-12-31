package com.parachute.booking.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceCreate adminServiceCreate;
    private final AdminServiceGetAll adminServiceGetAll;


    @PostMapping("/admin")
    ResponseEntity<AdminDto> createNewAdmin(AdminDto adminDto) {

        return null;
    }


    @GetMapping("/admin")
    List<AdminDto> getAllAdmins() {

        return null;
    }


}
