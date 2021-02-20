package com.parachute.booking.flight;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parachute.booking.pilot.Pilot;
import com.parachute.booking.pilot.PilotRepository;
import com.parachute.booking.plane.Plane;
import com.parachute.booking.plane.PlaneRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class FlightServiceRemoveIntegrationTest {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PlaneRepository planeRepository;
    @Autowired
    private PilotRepository pilotRepository;
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

    private Flight createFlightForTest() {
        Plane plane = new Plane();
        plane.setPlaneNumber(11L);

        Pilot pilot = new Pilot();
        pilot.setPilotLicenseNumber(111L);

        Flight flight = Flight.builder()
                .id(1L)
                .planeNumber(plane)
                .pilotLicenseNumber(pilot)
                .localDateTime(localDateTime)
                .build();

        pilotRepository.save(pilot);
        planeRepository.save(plane);
        flightRepository.save(flight);

        return flight;
    }

    @Test
    void deleteFlight_andReturnStatusCode200() throws Exception {
        //given
        Flight flight = createFlightForTest();
        String requestParam = objectMapper.writeValueAsString(flight.getId());

        MockHttpServletRequestBuilder delete = delete(requestMappingUrl + "/{id}", requestParam)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(delete).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        List<Flight> flights = flightRepository.findAll();
        assertThat(flights.size()).isEqualTo(0);
    }

    @Test
    void deleteFlight_andReturnStatusCode400() throws Exception {
        //given
        Flight flight = flightRepository.save(createFlightForTest());
        int fakeId = 1;
        String requestParam = objectMapper.writeValueAsString(flight.getId() + fakeId);

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
