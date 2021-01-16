package com.parachute.booking.pilot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
class PilotServiceRemoveIntegrationTest {

    @Autowired
    private PilotRepository pilotRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final String requestMappingUrl = "/pilots";

    @BeforeEach
    void setup() {
        pilotRepository.deleteAll();
    }

    private Pilot createPilotForTest() {
        return Pilot.builder()
                .pilotLicenseNumber(888L)
                .firstName("Marian")
                .surName("Narciarz")
                .id(1L)
                .build();
    }

    @Test
    void deletePilot_andReturnStatusCode200() throws Exception {
        //given
        Pilot pilot = pilotRepository.save(createPilotForTest());
        String requestParam = objectMapper.writeValueAsString(pilot.getId());

        MockHttpServletRequestBuilder delete = delete(requestMappingUrl + "/{id}", requestParam)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(delete).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        List<Pilot> pilots = pilotRepository.findAll();
        assertThat(pilots.size()).isEqualTo(0);
    }

    @Test
    void deletePilot_andReturnStatusCode400() throws Exception {
        //given
        Pilot pilot = pilotRepository.save(createPilotForTest());
        int fakeId = 1;
        String requestParam = objectMapper.writeValueAsString(pilot.getId() + fakeId);

        MockHttpServletRequestBuilder delete = delete(requestMappingUrl + "/{id}", requestParam)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(delete).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
