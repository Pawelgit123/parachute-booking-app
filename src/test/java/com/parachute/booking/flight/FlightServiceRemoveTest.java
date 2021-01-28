package com.parachute.booking.flight;

import com.parachute.booking.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceRemoveTest {

    private static final Long ID = 1L;

    @Mock
    private FlightRepository flightRepository;
    @InjectMocks
    private FlightServiceRemove flightServiceRemove;
    LocalDateTime localDateTime;

    private Flight createFlightForTest() {
        return Flight.builder()
                .id(ID)
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
    void removePilotById_whenPilotExists() {
        //given
        when(flightRepository.findById(ID)).thenReturn(Optional.of(createFlightForTest()));

        //when
        flightServiceRemove.removeFlightById(ID);

        //then
        verify(flightRepository, times(1)).deleteById(ID);
    }

    @Test
    void removePilotById_whenPilotDoesntExist() {
        //given
        when(flightRepository.findById(ID)).thenReturn(Optional.empty());

        //when

        //then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> flightServiceRemove.removeFlightById(ID));
        verify(flightRepository, never()).deleteById(ID);
    }

}
