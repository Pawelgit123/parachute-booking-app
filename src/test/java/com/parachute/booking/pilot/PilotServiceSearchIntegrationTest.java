package com.parachute.booking.pilot;

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
class PilotServiceSearchIntegrationTest {

    @Autowired
    private PilotRepository pilotRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final String requestMappingUrl = "/pilots";

    private Pilot createPilotForTestA() {
        return Pilot.builder()
                .pilotLicenseNumber(111L)
                .firstName("Adam")
                .surName("Adamowski")
                .id(1L)
                .build();
    }

    private Pilot createPilotForTestB() {
        return Pilot.builder()
                .pilotLicenseNumber(22L)
                .firstName("Barabasz")
                .surName("Beria")
                .id(2L)
                .build();
    }

    private Pilot createPilotForTestC() {
        return Pilot.builder()
                .pilotLicenseNumber(332L)
                .firstName("Cyprian")
                .surName("Czaja")
                .id(2L)
                .build();
    }

    @BeforeEach
    void setup() {
        pilotRepository.deleteAll();
    }

    @Test
    void getAllPilots_andReturnsStatusCOde200() throws Exception {
        //given
        pilotRepository.save(createPilotForTestA());
        pilotRepository.save(createPilotForTestB());
        pilotRepository.save(createPilotForTestC());

        MockHttpServletRequestBuilder request = get(requestMappingUrl);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        PilotDtoListed responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
        });
        assertThat(responseBody.getPilots()).hasSize(3);
    }

    @Test
    void getAllPilots_andReturnsStatusCOde200_noPilots() throws Exception {
        //given
        MockHttpServletRequestBuilder request = get(requestMappingUrl);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        PilotDtoListed responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
        });
        assertThat(responseBody.getPilots()).hasSize(0);

    }

    @Test
    void findPilotById_andReturnsStatusCode200() throws Exception {
        //given
        Pilot save = pilotRepository.save(createPilotForTestA());
        pilotRepository.save(createPilotForTestB());
        pilotRepository.save(createPilotForTestC());

        Long id = save.getId();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/{id}", id);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        PilotDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), PilotDto.class);
        assertThat(respondeBody.getId()).isEqualTo(id);
        assertThat(respondeBody.getFirstName()).isEqualTo("Adam");
        assertThat(respondeBody.getSurName()).isEqualTo("Adamowski");
        assertThat(respondeBody.getPilotLicenseNumber()).isEqualTo(111L);
    }

    @Test
    void findById_andReturnsStatusCode400_pilotDoesntExists() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/{id}", 100);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findById_andReturnsStatusCode400_idIsNegative() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/{id}", -1);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }


    @Test
    void findPilotByLicenseNumber_andReturnsStatusCode200() throws Exception {
        //given
        Pilot save = pilotRepository.save(createPilotForTestA());
        pilotRepository.save(createPilotForTestB());
        pilotRepository.save(createPilotForTestC());

        Long license = save.getPilotLicenseNumber();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/license/{license}", license);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        PilotDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), PilotDto.class);
        assertThat(respondeBody.getId()).isEqualTo(save.getId());
        assertThat(respondeBody.getFirstName()).isEqualTo("Adam");
        assertThat(respondeBody.getSurName()).isEqualTo("Adamowski");
        assertThat(respondeBody.getPilotLicenseNumber()).isEqualTo(license);
    }

    @Test
    void findPilotByLicense_andReturnsStatusCode400() throws Exception {
        // given
        pilotRepository.save(createPilotForTestA());
        pilotRepository.save(createPilotForTestB());
        pilotRepository.save(createPilotForTestC());
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/plane/{plane}", 100L);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findPilotByFirstName_andReturnsStatusCode200() throws Exception {
        //given
        Pilot save = pilotRepository.save(createPilotForTestA());
        pilotRepository.save(createPilotForTestB());
        pilotRepository.save(createPilotForTestC());

        String name = save.getFirstName();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/name/{name}", name);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        PilotDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), PilotDto.class);
        assertThat(respondeBody.getId()).isEqualTo(save.getId());
        assertThat(respondeBody.getFirstName()).isEqualTo(name);
        assertThat(respondeBody.getSurName()).isEqualTo("Adamowski");
        assertThat(respondeBody.getPilotLicenseNumber()).isEqualTo(111L);
    }

    @Test
    void findPilotByFirstName_andReturnsStatusCode400() throws Exception {
        // given
        pilotRepository.save(createPilotForTestA());
        pilotRepository.save(createPilotForTestB());
        pilotRepository.save(createPilotForTestC());
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/name/{name}", "Bob");

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findPilotBySurName_andReturnsStatusCode200() throws Exception {
        //given
        Pilot save = pilotRepository.save(createPilotForTestA());
        pilotRepository.save(createPilotForTestB());
        pilotRepository.save(createPilotForTestC());

        String surname = save.getSurName();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/surname/{surname}", surname);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        PilotDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), PilotDto.class);
        assertThat(respondeBody.getId()).isEqualTo(save.getId());
        assertThat(respondeBody.getFirstName()).isEqualTo("Adam");
        assertThat(respondeBody.getSurName()).isEqualTo(surname);
        assertThat(respondeBody.getPilotLicenseNumber()).isEqualTo(111L);
    }

    @Test
    void findPilotBySurName_andReturnsStatusCode400() throws Exception {
        // given
        pilotRepository.save(createPilotForTestA());
        pilotRepository.save(createPilotForTestB());
        pilotRepository.save(createPilotForTestC());
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/surname/{surname}", "Johnson");

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

}
