package com.parachute.booking.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceUpdate {

    private final FlightRepository flightRepository;

    //TODO update - plane, pilot, date, hour, clients, forecast
    //block update for finished flights?
}
