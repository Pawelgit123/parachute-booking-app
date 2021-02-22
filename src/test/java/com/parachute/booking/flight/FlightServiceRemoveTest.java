package com.parachute.booking.flight;

import com.parachute.booking.exceptions.NotFoundException;
import com.parachute.booking.pilot.Pilot;
import com.parachute.booking.plane.Plane;
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

        Plane plane = new Plane();
        plane.setPlaneNumber(22L);

        Pilot pilot = new Pilot();
        pilot.setPilotLicenseNumber(222L);

        return Flight.builder()
                .id(ID)
                .planeNumber(plane)
                .pilotLicenseNumber(pilot)
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
