package com.parachute.booking.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceUpdate {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    //TODO update - plane, pilot, date, hour, clients, forecast
    //TODO block update for finished flights?
}
