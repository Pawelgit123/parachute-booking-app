package com.parachute.booking.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceCreate {

    private final FlightRepository flightRepository;
}
