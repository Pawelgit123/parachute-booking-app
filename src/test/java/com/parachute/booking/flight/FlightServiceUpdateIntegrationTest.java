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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class FlightServiceUpdateIntegrationTest {

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

    private void createFlightForTest() {
        Plane plane = new Plane();
        plane.setPlaneNumber(77L);

        Pilot pilot = new Pilot();
        pilot.setPilotLicenseNumber(777L);

        Flight flight = Flight.builder()
                .planeNumber(plane)
                .pilotLicenseNumber(pilot)
                .localDateTime(localDateTime)
                .build();

        planeRepository.save(plane);
        pilotRepository.save(pilot);
        flightRepository.save(flight);
    }

    private void createFlightDtoForTest() {
        FlightDto flightDto = FlightDto.builder()
                .planeNumber(77L)
                .pilotLicenseNumber(777L)
                .localDateTime(localDateTime)
                .build();
    }

    private void createFlightDtoForTestOnlyPlane() {
        Plane plane = new Plane();
        plane.setPlaneNumber(77L);
        planeRepository.save(plane);

        FlightDto flightDto = FlightDto.builder()
                .planeNumber(77L)
                .build();
    }

    private void createFlightDtoForTestOnlyPilot() {
        Pilot pilot = new Pilot();
        pilot.setPilotLicenseNumber(777L);
        pilotRepository.save(pilot);

        FlightDto flightDto = FlightDto.builder()
                .pilotLicenseNumber(777L)
                .build();
    }

    private void createFlightDtoForTestOnlyDate() {
        FlightDto flightDto = FlightDto.builder()
                .localDateTime(localDateTime)
                .build();
    }

    private void createPlaneAndPilotForTest() {
        Plane plane = new Plane();
        plane.setPlaneNumber(88L);

        Pilot pilot = new Pilot();
        pilot.setPilotLicenseNumber(888L);

        planeRepository.save(plane);
        pilotRepository.save(pilot);
    }

    @BeforeEach
    void setup() {
        flightRepository.deleteAll();
        planeRepository.deleteAll();
        pilotRepository.deleteAll();
    }

    @Test
    void updateFlight_andReturnsStatusCode200() throws Exception {
        //given
        Flight save = flightRepository.save(createFlightForTest());
        FlightDto flightDto = createFlightDtoForTest();
        Long id = save.getId();
        String requestbody = objectMapper.writeValueAsString(flightDto);
        MockHttpServletRequestBuilder request = put(requestMappingUrl + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestbody);
        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        List<Flight> flights = flightRepository.findAll();
        assertThat(flights.size()).isEqualTo(1);
        assertThat(flights.get(0)).satisfies(flight -> {
            assertThat(flight.getId()).isEqualTo(id);
            assertThat(flight.getPlaneNumber()).isEqualTo(flightDto.getPlaneNumber());
            assertThat(flight.getPilotLicenseNumber()).isEqualTo(flightDto.getPilotLicenseNumber());
            assertThat(flight.getLocalDateTime()).isEqualTo(flightDto.getLocalDateTime());
        });
    }

    @Test
    void updateFlight_andReturnsStatusCode400_noFlight() throws Exception {
        //given
        FlightDto flightDto = createFlightDtoForTest();
        String requestbody = objectMapper.writeValueAsString(flightDto);
        MockHttpServletRequestBuilder request = put(requestMappingUrl + "/{id}", 100L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestbody);
        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        List<Flight> flights = flightRepository.findAll();
        assertThat(flights.size()).isEqualTo(0);
    }

    @Test
    void patchFlight_andReturnsStatusCode200_planePatch() throws Exception {
        //given
        Flight save = flightRepository.save(createFlightForTest());
        FlightDto flightDto = createFlightDtoForTestOnlyPlane();
        Long id = save.getId();
        String requestbody = objectMapper.writeValueAsString(flightDto);
        MockHttpServletRequestBuilder request = put(requestMappingUrl + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestbody);
        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        List<Flight> flights = flightRepository.findAll();
        assertThat(flights.size()).isEqualTo(1);
        assertThat(flights.get(0)).satisfies(flight -> {
            assertThat(flight.getPlaneNumber()).isEqualTo(flightDto.getPlaneNumber());
            assertThat(flight.getId()).isEqualTo(id);
        });
    }

    @Test
    void patchFlight_andReturnsStatusCode200_pilotPatche() throws Exception {
        //given
        Flight save = flightRepository.save(createFlightForTest());
        FlightDto flightDto = createFlightDtoForTestOnlyPilot();
        Long id = save.getId();
        String requestbody = objectMapper.writeValueAsString(flightDto);
        MockHttpServletRequestBuilder request = put(requestMappingUrl + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestbody);
        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        List<Flight> flights = flightRepository.findAll();
        assertThat(flights.size()).isEqualTo(1);
        assertThat(flights.get(0)).satisfies(flight -> {
            assertThat(flight.getId()).isEqualTo(id);
            assertThat(flight.getPilotLicenseNumber()).isEqualTo(flightDto.getPilotLicenseNumber());
        });
    }

    @Test
    void patchFlight_andReturnsStatusCode200_datePatch() throws Exception {
        //given
        Flight save = flightRepository.save(createFlightForTest());
        FlightDto flightDto = createFlightDtoForTestOnlyDate();
        Long id = save.getId();
        String requestbody = objectMapper.writeValueAsString(flightDto);
        MockHttpServletRequestBuilder request = put(requestMappingUrl + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestbody);
        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        List<Flight> flights = flightRepository.findAll();
        assertThat(flights.size()).isEqualTo(1);
        assertThat(flights.get(0)).satisfies(flight -> {
            assertThat(flight.getId()).isEqualTo(id);
            assertThat(flight.getLocalDateTime()).isEqualTo(flightDto.getLocalDateTime());
        });
    }

}
