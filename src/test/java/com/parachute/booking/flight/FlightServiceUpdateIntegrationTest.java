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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class FlightServiceUpdateIntegrationTest {

//    @Autowired
//    private FlightRepository flightRepository;
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//    LocalDateTime localDateTime;
//
//    private final String requestMappingUrl = "/flights";
//
//    private Flight createFlightForTest() {
//        Plane plane = new Plane();
//        plane.setPlaneNumber(22L);
//
//        Pilot pilot = new Pilot();
//        pilot.setPilotLicenseNumber(222L);
//
//        return Flight.builder()
//                .id(2L)
//                .planeNumber(plane)
//                .pilotLicenseNumber(pilot)
//                .localDateTime(localDateTime)
//                .build();
//    }
//
//    private FlightDto createFlightDtoForTest() {
//        return FlightDto.builder()
//                .planeNumber(11L)
//                .pilotLicenseNumber(111L)
//                .localDateTime(localDateTime)
//                .build();
//    }
//
//    private FlightDto createFlightDtoForTestOnlyPlane() {
//        return FlightDto.builder()
//                .planeNumber(11L)
//                .build();
//    }
//
//    private FlightDto createFlightDtoForTestOnlyPilot() {
//        return FlightDto.builder()
//                .pilotLicenseNumber(111L)
//                .build();
//    }
//
//    private FlightDto createFlightDtoForTestOnlyDate() {
//        return FlightDto.builder()
//                .localDateTime(localDateTime)
//                .build();
//    }
//
//    @BeforeEach
//    void setup() {
//        flightRepository.deleteAll();
//    }
//
//    @Test
//    void updateFlightById_andReturnsStatusCode200() throws Exception {
//        //given
//        Flight save = flightRepository.save(createFlightForTest());
//        FlightDto flightDto = createFlightDtoForTest();
//        Long id = save.getId();
//        String requestbody = objectMapper.writeValueAsString(flightDto);
//        MockHttpServletRequestBuilder request = put(requestMappingUrl + "/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestbody);
//        //when
//        MvcResult result = mockMvc.perform(request).andReturn();
//
//        //then
//        MockHttpServletResponse response = result.getResponse();
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
//        List<Flight> flights = flightRepository.findAll();
//        assertThat(flights.size()).isEqualTo(1);
//        assertThat(flights.get(0)).satisfies(flight -> {
//            assertThat(flight.getId()).isEqualTo(id);
//            assertThat(flight.getPlaneNumber()).isEqualTo(flightDto.getPlaneNumber());
//            assertThat(flight.getPilotLicenseNumber()).isEqualTo(flightDto.getPilotLicenseNumber());
//            assertThat(flight.getLocalDateTime()).isEqualTo(flightDto.getLocalDateTime());
//        });
//    }
//
//    @Test
//    void updateFlightById_andReturnsStatusCode400_noFlight() throws Exception {
//        //given
//        FlightDto flightDto = createFlightDtoForTest();
//        String requestbody = objectMapper.writeValueAsString(flightDto);
//        MockHttpServletRequestBuilder request = put(requestMappingUrl + "/{id}", 100L)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestbody);
//        //when
//        MvcResult result = mockMvc.perform(request).andReturn();
//
//        //then
//        MockHttpServletResponse response = result.getResponse();
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
//        List<Flight> flights = flightRepository.findAll();
//        assertThat(flights.size()).isEqualTo(0);
//    }
//
//    @Test
//    void updateFlightById_andReturnsStatusCode200_onlyPlaneNumberUpdate() throws Exception {
//        //given
//        Flight save = flightRepository.save(createFlightForTest());
//        FlightDto flightDto = createFlightDtoForTestOnlyPlane();
//        Long id = save.getId();
//        String requestbody = objectMapper.writeValueAsString(flightDto);
//        MockHttpServletRequestBuilder request = put(requestMappingUrl + "/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestbody);
//        //when
//        MvcResult result = mockMvc.perform(request).andReturn();
//
//        //then
//        MockHttpServletResponse response = result.getResponse();
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
//        List<Flight> flights = flightRepository.findAll();
//        assertThat(flights.size()).isEqualTo(1);
//        assertThat(flights.get(0)).satisfies(flight -> {
//            assertThat(flight.getPlaneNumber()).isEqualTo(flightDto.getPlaneNumber());
//            assertThat(flight.getId()).isEqualTo(id);
//        });
//    }
//
//    @Test
//    void updateFlightById_andReturnsStatusCode200_onlyPilotUpdtte() throws Exception {
//        //given
//        Flight save = flightRepository.save(createFlightForTest());
//        FlightDto flightDto = createFlightDtoForTestOnlyPilot();
//        Long id = save.getId();
//        String requestbody = objectMapper.writeValueAsString(flightDto);
//        MockHttpServletRequestBuilder request = put(requestMappingUrl + "/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestbody);
//        //when
//        MvcResult result = mockMvc.perform(request).andReturn();
//
//        //then
//        MockHttpServletResponse response = result.getResponse();
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
//        List<Flight> flights = flightRepository.findAll();
//        assertThat(flights.size()).isEqualTo(1);
//        assertThat(flights.get(0)).satisfies(flight -> {
//            assertThat(flight.getId()).isEqualTo(id);
//            assertThat(flight.getPilotLicenseNumber()).isEqualTo(flightDto.getPilotLicenseNumber());
//        });
//    }
//
//    @Test
//    void updateFlightById_andReturnsStatusCode200_onlyDateUpdate() throws Exception {
//        //given
//        Flight save = flightRepository.save(createFlightForTest());
//        FlightDto flightDto = createFlightDtoForTestOnlyDate();
//        Long id = save.getId();
//        String requestbody = objectMapper.writeValueAsString(flightDto);
//        MockHttpServletRequestBuilder request = put(requestMappingUrl + "/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestbody);
//        //when
//        MvcResult result = mockMvc.perform(request).andReturn();
//
//        //then
//        MockHttpServletResponse response = result.getResponse();
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
//        List<Flight> flights = flightRepository.findAll();
//        assertThat(flights.size()).isEqualTo(1);
//        assertThat(flights.get(0)).satisfies(flight -> {
//            assertThat(flight.getId()).isEqualTo(id);
//            assertThat(flight.getLocalDateTime()).isEqualTo(flightDto.getLocalDateTime());
//        });
//    }

}
