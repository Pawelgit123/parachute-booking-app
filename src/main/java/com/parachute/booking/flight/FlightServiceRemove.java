package com.parachute.booking.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceRemove {

    private final FlightRepository flightRepository;
}
