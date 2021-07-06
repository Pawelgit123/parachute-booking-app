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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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

    private Flight createFlightForTest() {
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

        return flight;
    }

    private FlightDto createFlightDtoForTest(Long id) {
        return FlightDto.builder()
                .id(id)
                .planeNumber(88L)
                .pilotLicenseNumber(888L)
                .localDateTime(localDateTime)
                .build();
    }

    private FlightDto createFlightDtoForTestOnlyPlane(Long id) {
        Plane plane = new Plane();
        plane.setPlaneNumber(88L);
        planeRepository.save(plane);

        return FlightDto.builder()
                .id(id)
                .planeNumber(88L)
                .build();
    }

    private FlightDto createFlightDtoForTestOnlyPilot(Long id) {
        Pilot pilot = new Pilot();
        pilot.setPilotLicenseNumber(888L);
        pilotRepository.save(pilot);

        return FlightDto.builder()
                .id(id)
                .pilotLicenseNumber(888L)
                .build();
    }

    private FlightDto createFlightDtoForTestOnlyDate(Long id) {
        return FlightDto.builder()
                .id(id)
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
        Flight flightForTest = createFlightForTest();
        createPlaneAndPilotForTest();
        FlightDto flightDto = createFlightDtoForTest(flightForTest.getId());
        Long id = flightDto.getId();
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
        assertThat(flights.get(0)).satisfies(flight -> {
            assertThat(flight.getPlaneNumber().getPlaneNumber()).isEqualTo(flightDto.getPlaneNumber());
            assertThat(flight.getPilotLicenseNumber().getPilotLicenseNumber()).isEqualTo(flightDto.getPilotLicenseNumber());
            assertThat(flight.getId()).isEqualTo(id);
        });
    }

    @Test
    void updateFlight_andReturnsStatusCode400_noFlight() throws Exception {
        //given
        Flight flightForTest = createFlightForTest();
        createPlaneAndPilotForTest();
        FlightDto flightDto = createFlightDtoForTest(flightForTest.getId());
        String requestbody = objectMapper.writeValueAsString(flightDto);
        MockHttpServletRequestBuilder request = put(requestMappingUrl + "/{id}", 1000L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestbody);
        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        List<Flight> flights = flightRepository.findAll();
        assertThat(flights.size()).isEqualTo(1);
    }

    @Test
    void patchFlight_andReturnsStatusCode200_planePatch() throws Exception {
        //given
        Flight flightForTest = createFlightForTest();
        FlightDto flightDto = createFlightDtoForTestOnlyPlane(flightForTest.getId());
        Long id = flightDto.getId();
        String requestbody = objectMapper.writeValueAsString(flightDto);
        MockHttpServletRequestBuilder request = patch(requestMappingUrl + "/{id}", id)
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
            assertThat(flight.getPlaneNumber().getPlaneNumber()).isEqualTo(flightDto.getPlaneNumber());
            assertThat(flight.getId()).isEqualTo(id);
        });
    }

    @Test
    void patchFlight_andReturnsStatusCode200_pilotPatch() throws Exception {
        //given
        Flight flightForTest = createFlightForTest();
        FlightDto flightDto = createFlightDtoForTestOnlyPilot(flightForTest.getId());
        Long id = flightDto.getId();
        String requestbody = objectMapper.writeValueAsString(flightDto);
        MockHttpServletRequestBuilder request = patch(requestMappingUrl + "/{id}", id)
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
            assertThat(flight.getPilotLicenseNumber().getPilotLicenseNumber()).isEqualTo(flightDto.getPilotLicenseNumber());
        });
    }

    @Test
    void patchFlight_andReturnsStatusCode200_datePatch() throws Exception {
        //given
        Flight flightForTest = createFlightForTest();
        FlightDto flightDto = createFlightDtoForTestOnlyDate(flightForTest.getId());
        Long id = flightDto.getId();
        String requestbody = objectMapper.writeValueAsString(flightDto);
        MockHttpServletRequestBuilder request = patch(requestMappingUrl + "/{id}", id)
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
