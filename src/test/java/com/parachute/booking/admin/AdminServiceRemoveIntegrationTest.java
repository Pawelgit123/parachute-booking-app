package com.parachute.booking.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class AdminServiceRemoveIntegrationTest {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final String requestMappingUrl = "/admins";

    @BeforeEach
    void setup(){
        adminRepository.deleteAll();
    }

    private Admin createNewAdminForTestA() {
        return Admin.builder()
                .login("Admin2")
                .password("Admin pass")
                .email("admin@gmail.com")
                .build();
    }

    @Test
    void deleteAdmin_andReturnStatusCode200() throws Exception {
        //given
        Admin savedAdmin = adminRepository.save(createNewAdminForTestA());
        String requestParam = objectMapper.writeValueAsString(savedAdmin.getId());

        MockHttpServletRequestBuilder delete = delete(requestMappingUrl+"/{id}", requestParam)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(delete).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        List<Admin> admins = adminRepository.findAll();
        assertThat(admins.size()).isEqualTo(0);
    }

    @Test
    void deleteAdmin_andReturnStatusCode400() throws Exception {
        //given
        Admin savedAdmin = adminRepository.save(createNewAdminForTestA());
        int fakeId = 1;
        String requestParam = objectMapper.writeValueAsString(savedAdmin.getId()+fakeId);

        MockHttpServletRequestBuilder delete = delete(requestMappingUrl+"/{id}", requestParam)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(delete).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

}
