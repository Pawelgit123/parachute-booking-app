package com.parachute.booking.flight;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightServiceCreateTest {

  @Mock
  private FlightRepository flightRepository;

  @Mock
  private FlightMapper flightMapper;

  @InjectMocks
  private FlightServiceCreate flightServiceCreate;

  @Test
  void diasijqeda() {
    System.out.println("");
    //when(flightMapper.mapFlight(null)).thenReturn(null);
    //when(flightMapper.mapFlightDto(null)).thenReturn(null);
  }
}
