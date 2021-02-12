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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class AdminServiceSearchIntegrationTest {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    private final String requestMappingUrl = "/admins";

    private Admin createNewAdminForTestA() {
        return Admin.builder()
                .login("Admin2")
                .password("Admin pass")
                .email("admin@gmail.com")
                .build();
    }

    private Admin createNewAdminForTestB() {
        return Admin.builder()
                .login("Admin3")
                .password("Admin is stupid")
                .email("lololo@gmail.com")
                .build();
    }

    private Admin createNewAdminForTestC() {
        return Admin.builder()
                .login("Admin4")
                .password("Admin secret code")
                .email("admin@wp.plm")
                .build();
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
        MockHttpServletRequestBuilder request = get(requestMappingUrl);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        AdminDtoListed responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
        });
        assertThat(responseBody.getAdmins()).hasSize(3);
    }

    @Test
    void getAllAdmins_andReturnsStatusCode200_noAdmins() throws Exception {
        //given
        MockHttpServletRequestBuilder request = get(requestMappingUrl);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        AdminDtoListed responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
        });
        assertThat(responseBody.getAdmins()).hasSize(0);
    }

    @Test
    void findById_andReturnsStatusCode200() throws Exception {
        //given
        Admin savedAdmin = adminRepository.save(createNewAdminForTestA());
        adminRepository.save(createNewAdminForTestB());
        adminRepository.save(createNewAdminForTestC());
        Long id = savedAdmin.getId();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/{id}", id);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        AdminDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), AdminDto.class);
        assertThat(respondeBody.getId()).isEqualTo(id);
        assertThat(respondeBody.getLogin()).isEqualTo("Admin2");
        assertThat(respondeBody.getEmail()).isEqualTo("admin@gmail.com");
    }

    @Test
    void findById_andReturnsStatusCode400_adminDoesntExists() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/{id}", 100);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findById_andReturnsStatusCode400_adminIdIsNegative() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/{id}", -1);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findByLogin_andReturnsStatusCode200() throws Exception {
        //given
        Admin savedAdmin = adminRepository.save(createNewAdminForTestA());
        adminRepository.save(createNewAdminForTestB());
        adminRepository.save(createNewAdminForTestC());
        String login = savedAdmin.getLogin();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/login/{login}", login);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        AdminDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), AdminDto.class);
        assertThat(respondeBody.getLogin()).isEqualTo("Admin2");
        assertThat(respondeBody.getEmail()).isEqualTo("admin@gmail.com");
    }

    @Test
    void findByLogin_andReturnsStatusCode400() throws Exception {
        //given
        Admin newAdminForTestA = createNewAdminForTestA();
        String login = newAdminForTestA.getLogin();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/login/{login}", login);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findByEmail_andReturnsStatusCode200() throws Exception {
        //given
        Admin save = adminRepository.save(createNewAdminForTestA());
        String email = save.getEmail();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/email/{email}", email);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        AdminDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), AdminDto.class);
        assertThat(respondeBody.getLogin()).isEqualTo("Admin2");
        assertThat(respondeBody.getEmail()).isEqualTo("admin@gmail.com");
    }

    @Test
    void findByEmail_andReturnsStatusCode400() throws Exception {
        //given
        Admin newAdminForTestA = createNewAdminForTestA();
        String email = newAdminForTestA.getEmail();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/email/{email}", email);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

}
