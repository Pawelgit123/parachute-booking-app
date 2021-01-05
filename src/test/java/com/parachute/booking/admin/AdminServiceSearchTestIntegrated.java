package com.parachute.booking.admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminServiceSearchTestIntegrated {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        adminRepository.deleteAll();
    }

    private Admin createNewAdminForTest() {
        Admin admin = new Admin.AdminBuilder()
                .login("Admin1")
                .password("Admin pass")
                .email("admin@gmail.com")
                .build();
        return admin;
    }

    @Test
    void getAllAdmins_andReturnsStatusCode200() throws Exception {
        //given
        adminRepository.save(createNewAdminForTest());
        adminRepository.save(createNewAdminForTest());
        adminRepository.save(createNewAdminForTest());
        MockHttpServletRequestBuilder request = get("/admin");

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<AdminDto> responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertThat(responseBody).hasSize(3);
    }

    @Test
    void getAllAdmins_andReturnsStatusCode200_noAdmins() throws Exception {
        //given
        MockHttpServletRequestBuilder request = get("/admin");

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<AdminDto> responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertThat(responseBody).hasSize(0);
    }

    @Test
    void fingById_andReturnsStatusCode200() throws Exception {
        //given
        adminRepository.save(createNewAdminForTest());
        adminRepository.save(createNewAdminForTest());
        Admin savedAdmin = adminRepository.save(createNewAdminForTest());
        Long id = savedAdmin.getId();
        MockHttpServletRequestBuilder request = get("/admin/{id}", id);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        AdminDto respondeBody =objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), AdminDto.class);
        assertThat(respondeBody.getId()).isEqualTo(id);
        assertThat(respondeBody.getLogin()).isEqualTo("Admin1");
        assertThat(respondeBody.getPassword()).isEqualTo("Admin pass");
        assertThat(respondeBody.getEmail()).isEqualTo("admin@gmail.com");
    }

    @Test
    void fingById_andReturnsStatusCode400_adminDoesntExists() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get("/admin/{id}", 100);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findByLogin_andReturnsStatusCode200() {
    }

    @Test
    void findByLogin_andReturnsStatusCode400() {
    }

    @Test
    void findByEmail_andReturnsStatusCode200() {
    }

    @Test
    void findByEmail_andReturnsStatusCode400() {
    }
}
