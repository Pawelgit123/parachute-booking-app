package com.parachute.booking.flight;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class FlightServiceSearchIntegrationTest {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    LocalDateTime localDateTime;

    private final String requestMappingUrl = "/flights";

    private Flight createFlightForTestA() {
        return Flight.builder()
                .id(1L)
                .planeNumber(11L)
                .pilotLicenseNumber(111L)
                .localDateTime(localDateTime)
                .build();
    }

    private Flight createFlightForTestB() {
        return Flight.builder()
                .id(2L)
                .planeNumber(22L)
                .pilotLicenseNumber(222L)
                .localDateTime(localDateTime)
                .build();
    }

    private Flight createFlightForTestC() {
        return Flight.builder()
                .id(3L)
                .planeNumber(33L)
                .pilotLicenseNumber(333L)
                .localDateTime(localDateTime)
                .build();
    }

    @BeforeEach
    void setup() {
        flightRepository.deleteAll();
    }

    @Test
    void getAllFlights_andReturnsStatusCode200() throws Exception {
        //given
        flightRepository.save(createFlightForTestA());
        flightRepository.save(createFlightForTestB());
        flightRepository.save(createFlightForTestC());

        MockHttpServletRequestBuilder request = get(requestMappingUrl);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        FlightDtoListed responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
        });
        assertThat(responseBody.getFlights()).hasSize(3);
    }

    @Test
    void getAllFlights_andReturnsStatusCode200_noAdmins() throws Exception {
        //given
        MockHttpServletRequestBuilder request = get(requestMappingUrl);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        FlightDtoListed responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
        });
        assertThat(responseBody.getFlights()).hasSize(0);
    }

    @Test
    void findById_andReturnsStatusCode200() throws Exception {
        //given
        Flight flight = flightRepository.save(createFlightForTestA());
        flightRepository.save(createFlightForTestB());
        flightRepository.save(createFlightForTestC());
        Long id = flight.getId();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/{id}", id);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        FlightDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), FlightDto.class);
        assertThat(respondeBody.getId()).isEqualTo(id);
        assertThat(respondeBody.getPlaneNumber()).isEqualTo(11L);
        assertThat(respondeBody.getPilotLicenseNumber()).isEqualTo(111L);
        assertThat(respondeBody.getLocalDateTime()).isEqualTo(localDateTime);
    }

    @Test
    void findById_andReturnsStatusCode400_flightDoesntExists() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/{id}", 100);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findById_andReturnsStatusCode400_FlightIdIsNegative() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/{id}", -1);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findByPlaneNumber_andReturnsStatusCode200() throws Exception {
        //given
        Flight flight = flightRepository.save(createFlightForTestA());
        flightRepository.save(createFlightForTestB());
        flightRepository.save(createFlightForTestC());
        Long plane = flight.getPlaneNumber();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/plane/{plane}", plane);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        FlightDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), FlightDto.class);
        assertThat(respondeBody.getPlaneNumber()).isEqualTo(plane);
        assertThat(respondeBody.getPilotLicenseNumber()).isEqualTo(flight.getPilotLicenseNumber());
        assertThat(respondeBody.getLocalDateTime()).isEqualTo(localDateTime);

    }

    @Test
    void findByPlanenumber_andReturnsStatusCode400() throws Exception {
        // given
        flightRepository.save(createFlightForTestA());
        flightRepository.save(createFlightForTestB());
        flightRepository.save(createFlightForTestC());
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/plane/{plane}", 100L);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findByPilotLicense_andReturnsStatusCode200() throws Exception {
        //given
        Flight flight = flightRepository.save(createFlightForTestA());
        flightRepository.save(createFlightForTestB());
        flightRepository.save(createFlightForTestC());
        Long pilot = flight.getPilotLicenseNumber();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/pilot/{pilot}", pilot);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        FlightDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), FlightDto.class);
        assertThat(respondeBody.getPlaneNumber()).isEqualTo(createFlightForTestA().getPlaneNumber());
        assertThat(respondeBody.getPilotLicenseNumber()).isEqualTo(pilot);
        assertThat(respondeBody.getLocalDateTime()).isEqualTo(localDateTime);

    }

    @Test
    void findByPilotLicense_andReturnsStatusCode400() throws Exception {
        // given
        flightRepository.save(createFlightForTestA());
        flightRepository.save(createFlightForTestB());
        flightRepository.save(createFlightForTestC());
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/pilot/{pilot}", 10L);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    //TODO date time

    @Test
    void findByLocalDateTime_andReturnsStatusCode200(){

    }

    @Test
    void findByLocalDateTime_andReturnsStatusCode400() {

    }

}
