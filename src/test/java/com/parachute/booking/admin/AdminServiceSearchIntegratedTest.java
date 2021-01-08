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

@SpringBootTest
@AutoConfigureMockMvc
class AdminServiceSearchIntegratedTest {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    private Admin createNewAdminForTestA() {
        Admin admin = new Admin.AdminBuilder()
                .login("Admin2")
                .password("Admin pass")
                .email("admin@gmail.com")
                .build();
        return admin;
    }

    private Admin createNewAdminForTestB() {
        Admin admin = new Admin.AdminBuilder()
                .login("Admin3")
                .password("Admin is stupid")
                .email("lololo@gmail.com")
                .build();
        return admin;
    }

    private Admin createNewAdminForTestC() {
        Admin admin = new Admin.AdminBuilder()
                .login("Admin4")
                .password("Admin secret code")
                .email("admin@wp.plm")
                .build();
        return admin;
    }

    @BeforeEach
    void setUp() {
        adminRepository.deleteAll();
    }

    @Test
    void getAllAdmins_andReturnsStatusCode200() throws Exception {
        //given
        adminRepository.save(createNewAdminForTestA());
        adminRepository.save(createNewAdminForTestB());
        adminRepository.save(createNewAdminForTestC());
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
    void findById_andReturnsStatusCode200() throws Exception {
        //given
        Admin savedAdmin = adminRepository.save(createNewAdminForTestA());
        adminRepository.save(createNewAdminForTestB());
        adminRepository.save(createNewAdminForTestC());
        Long id = savedAdmin.getId();
        MockHttpServletRequestBuilder request = get("/admin/{id}", id);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        AdminDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), AdminDto.class);
        assertThat(respondeBody.getId()).isEqualTo(id);
        assertThat(respondeBody.getLogin()).isEqualTo("Admin2");
        assertThat(respondeBody.getPassword()).isEqualTo("Admin pass");
        assertThat(respondeBody.getEmail()).isEqualTo("admin@gmail.com");
    }

    @Test
    void findById_andReturnsStatusCode400_adminDoesntExists() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get("/admin/{id}", 100);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findById_andReturnsStatusCode400_adminIdIsNegative() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get("/admin/{id}", -1);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

//    @Test
//    void findByLogin_andReturnsStatusCode200() throws Exception {
//        //given
//        Admin savedAdmin = adminRepository.save(createNewAdminForTestA());
//        adminRepository.save(createNewAdminForTestB());
//        adminRepository.save(createNewAdminForTestC());
//        String login = savedAdmin.getLogin();
//        MockHttpServletRequestBuilder request = get("/admin/{login}", login);
//
//        //when
//        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
//
//        //then
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        AdminDto respondeBody =objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), AdminDto.class);
//        assertThat(respondeBody.getLogin()).isEqualTo("Admin1");
//        assertThat(respondeBody.getPassword()).isEqualTo("Admin pass");
//        assertThat(respondeBody.getEmail()).isEqualTo("admin@gmail.com");
//    }
//
//    @Test
//    void findByLogin_andReturnsStatusCode400() {
//    }
//
//    @Test
//    void findByEmail_andReturnsStatusCode200() {
//    }
//
//    @Test
//    void findByEmail_andReturnsStatusCode400() {
//    }

}
