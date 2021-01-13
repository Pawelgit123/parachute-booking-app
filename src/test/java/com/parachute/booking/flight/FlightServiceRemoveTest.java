package com.parachute.booking.flight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FlightServiceRemoveTest {

    @Mock
    FlightRepository flightRepository;
    @Mock
    FlightServiceRemove flightServiceRemove;
    LocalDateTime localDateTime;

    private Flight createFlightForTest() {
        return Flight.builder()
                .id(1L)
                .planeNumber(22L)
                .pilotLicenseNumber(222L)
                .localDateTime(localDateTime)
                .build();
    }

    @BeforeEach
    void setup() {
        flightRepository.deleteAll();
        flightRepository.save(createFlightForTest());
    }


    @Test
    void removeFlightById() {
        //given

        //when
        flightServiceRemove.removeFlightById(createFlightForTest().getId());

        //tnen
        assertThat(flightRepository.findAll()).isEmpty();
        verify(flightServiceRemove, times(1)).removeFlightById(createFlightForTest().getId());
    }
}
