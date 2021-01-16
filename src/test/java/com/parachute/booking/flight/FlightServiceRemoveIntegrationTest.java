package com.parachute.booking.flight;

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

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
class FlightServiceRemoveIntegrationTest {

    @Autowired
    FlightRepository flightRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    LocalDateTime localDateTime;

    private final String requestMappingUrl = "/flights";

    @BeforeEach
    void setup() {
        flightRepository.deleteAll();
    }

    private Flight createFlightForTest() {
        return Flight.builder()
                .id(1L)
                .planeNumber(44L)
                .pilotLicenseNumber(444L)
                .localDateTime(localDateTime)
                .build();
    }

    @Test
    void deleteFlight_andReturnStatusCode200() throws Exception {
        //given
        Flight flight = flightRepository.save(createFlightForTest());
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
