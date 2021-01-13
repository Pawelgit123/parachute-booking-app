package com.parachute.booking.flight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FlightMapperTest {

    @Mock
    FlightRepository flightRepository;
    LocalDateTime localDateTime;

    private FlightDto createFlightDtoForTest() {
        return FlightDto.builder()
                .id(1L)
                .planeNumber(11L)
                .pilotLicenseNumber(111L)
                .localDateTime(localDateTime)
                .build();
    }

    private Flight createFlightForTest() {
        return Flight.builder()
                .id(2L)
                .planeNumber(22L)
                .pilotLicenseNumber(222L)
                .localDateTime(localDateTime)
                .build();
    }

    @BeforeEach
    void setup() {
        flightRepository.deleteAll();
    }

    @Test
    void mapFlightDto() {
        //given
        FlightMapper flightMapper = new FlightMapper();

        //when
        FlightDto flightDto = flightMapper.mapFlightDto(createFlightForTest());

        //then
        assertThat(flightDto).isExactlyInstanceOf(FlightDto.class);
        assertThat(flightDto.getId()).isEqualTo(2L);
        assertThat(flightDto.getPlaneNumber()).isEqualTo(22L);
        assertThat(flightDto.getPilotLicenseNumber()).isEqualTo(222L);
        assertThat(flightDto.getLocalDateTime()).isEqualTo(localDateTime);
    }

    @Test
    void mapFlight() {
        //given
        FlightMapper flightMapper = new FlightMapper();

        //when
        Flight flight = flightMapper.mapFlight(createFlightDtoForTest());

        //then
        assertThat(flight).isExactlyInstanceOf(Flight.class);
        assertThat(flight.getId()).isEqualTo(1L);
        assertThat(flight.getPlaneNumber()).isEqualTo(11L);
        assertThat(flight.getPilotLicenseNumber()).isEqualTo(111L);
        assertThat(flight.getLocalDateTime()).isEqualTo(localDateTime);
    }
}
