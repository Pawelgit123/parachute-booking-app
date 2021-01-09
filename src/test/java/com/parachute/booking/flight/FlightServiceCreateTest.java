package com.parachute.booking.flight;

import com.parachute.booking.exceptions.InternalServerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceCreateTest {

    @Mock
    FlightRepository flightRepository;

    @InjectMocks
    FlightServiceCreate flightServiceCreate;

    @Mock
    FlightMapper flightMapper;

    @Test
    void createNewFlight_saveFlightToRepository() {
        //given
        when(flightRepository.save(any(Flight.class))).thenReturn(new Flight());

        //when
        FlightDto newFlight = flightServiceCreate.createNewFlight(new FlightDto());

        //then
        assertThat(newFlight).isExactlyInstanceOf(FlightDto.class);
        verify(flightRepository).save(any(Flight.class));
    }

    @Test
    void createNewFlight_InternalServerError_byNull() {
        //when
        Throwable throwable = catchThrowable(() -> flightServiceCreate.createNewFlight(null));

        //then
        assertThat(throwable).isInstanceOf(InternalServerException.class);
        verify(flightRepository, times(0)).save(any(Flight.class));
    }
}
