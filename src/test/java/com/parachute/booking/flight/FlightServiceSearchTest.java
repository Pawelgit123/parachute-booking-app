package com.parachute.booking.flight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FlightServiceSearchTest {

    @Autowired
    FlightRepository flightRepository;
    @Autowired
    FlightServiceSearch flightServiceSearch;

    @BeforeEach
    void setup(){
        flightRepository.deleteAll();
    }

    private Flight createFlightsForTest(){
        Flight flight = new Flight();
        flight.setPlaneNumber(12345L);
        flight.setPilotLicenseNumber(1111L);
        return flight;
    }

    @Test
    void getAllFlights() {
        //given
        flightRepository.save(createFlightsForTest());
        flightRepository.save(createFlightsForTest());
        flightRepository.save(createFlightsForTest());

        //when
        Set<FlightDto> allFlights = flightServiceSearch.getAllFlights();

        //then
        assertThat(allFlights).size().isEqualTo(3);
    }

    @Test
    void getFlightById() {
        //given

        //when

        //then
    }
}
