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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class FlightServiceCreateIntegrationTest {

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

    private void createPlaneAndPilotForTest(){
        Plane plane = new Plane();
        plane.setPlaneNumber(11L);

        Pilot pilot = new Pilot();
        pilot.setPilotLicenseNumber(111L);

        pilotRepository.save(pilot);
        planeRepository.save(plane);
    }

    private FlightDto createFlightDtoForTest(){
        return FlightDto.builder()
                .planeNumber(11L)
                .pilotLicenseNumber(111L)
                .localDateTime(localDateTime)
                .build();
    }

    @Test
    void createFlight_andReturnStatusCode200() throws Exception {
        //given
        createPlaneAndPilotForTest();
        FlightDto flightDto = createFlightDtoForTest();
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
    }

    //TODO dorobić więcej

}
