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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class PilotServiceCreateIntegrationTest {

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

    private PilotDto createNewPilotDtoForTest() {

        return PilotDto.builder()
                .pilotLicenseNumber(555L)
                .firstName("Julian")
                .surName("Rak")
                .id(1L)
                .build();
    }

    @Test
    void createPilot_andReturnStatusCode200() throws Exception {
        //given
        PilotDto pilotDto = createNewPilotDtoForTest();
        String requestbody = objectMapper.writeValueAsString(pilotDto);
        MockHttpServletRequestBuilder request = post(requestMappingUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestbody);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        List<Pilot> pilots = pilotRepository.findAll();
        assertThat(pilots.size()).isEqualTo(1);
        assertThat(pilots.get(0)).satisfies(pilot -> {
            assertThat(pilot.getId()).isEqualTo(1L);
            assertThat(pilot.getFirstName()).isEqualTo("Julian");
            assertThat(pilot.getSurName()).isEqualTo("Rak");
            assertThat(pilot.getPilotLicenseNumber()).isEqualTo(555L);
        });
    }

}
