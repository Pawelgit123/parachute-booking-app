package com.parachute.booking.flight;

import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDto mapFlightDto(Flight flight) {

        return FlightDto.builder()
                .localDateTime(flight.getLocalDateTime())
                .id(flight.getId())
                .pilotLicenseNumber(flight.getPilotLicenseNumber())
                .planeNumber(flight.getPlaneNumber())
                .build();
    }

    public Flight mapFlight(FlightDto flightDto) {

        return Flight.builder()
                .localDateTime(flightDto.getLocalDateTime())
                .id(flightDto.getId())
                .pilotLicenseNumber(flightDto.getPilotLicenseNumber())
                .planeNumber(flightDto.getPlaneNumber())
                .build();
    }
}
