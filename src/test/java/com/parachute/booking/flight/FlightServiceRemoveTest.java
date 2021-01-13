package com.parachute.booking.flight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FlightServiceRemoveTest {

    @Mock
    FlightRepository flightRepository;
    @InjectMocks
    FlightServiceRemove flightServiceRemove;

    @BeforeEach
    void setup() {
        flightRepository.deleteAll();
    }

    public Flight createNeWFlightForTest() {

        Flight flight = new Flight();
        flight.setId(1L);
        flightRepository.save(flight);
        return flight;
    }

    @Test
    void removeFlightById() {
        //given
        Flight neWFlightForTest = createNeWFlightForTest();

        //when
        flightServiceRemove.removeFlightById(neWFlightForTest.getId());

        //tnen
        assertThat(flightRepository.findAll()).isEmpty();
        verify(flightRepository, times(1)).deleteById(neWFlightForTest.getId());
    }
}
