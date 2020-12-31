package com.parachute.booking.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceFind {

    private final FlightRepository flightRepository;
}
