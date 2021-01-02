package com.parachute.booking.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AdminServiceCreateTestIntegrated {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    MockMvc mockMvc;

}
