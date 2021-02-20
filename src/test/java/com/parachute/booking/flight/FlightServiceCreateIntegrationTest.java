package com.parachute.booking.flight;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parachute.booking.pilot.Pilot;
import com.parachute.booking.plane.Plane;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class FlightServiceCreateIntegrationTest {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    LocalDateTime localDateTime;

    private final String requestMappingUrl = "/flights";

    @BeforeEach
    void setup() {
        flightRepository.deleteAll();
    }

    private FlightDto createFlightDtoForTest() {

        return FlightDto.builder()
                .localDateTime(localDateTime)
                .build();
    }

    @Test
    void createFlight_andReturnStatusCode200() throws Exception {
        //given
        FlightDto flightDto = new FlightDto();
        String requestbody = objectMapper.writeValueAsString(flightDto);
        MockHttpServletRequestBuilder request = post(requestMappingUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestbody);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        List<Flight> flights = flightRepository.findAll();
        assertThat(flights.size()).isEqualTo(1);
        assertThat(flights.get(0)).satisfies(flight -> {
            assertThat(flight.getId()).isEqualTo(1L);
            assertThat(flight.getPlaneNumber().getPlaneNumber()).isEqualTo(99L);
            assertThat(flight.getPilotLicenseNumber().getPilotLicenseNumber()).isEqualTo(999L);
            assertThat(flight.getLocalDateTime()).isEqualTo(localDateTime);
        });
    }

    //TODO dorobić więcej

}
