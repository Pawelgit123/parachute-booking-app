package com.parachute.booking.flight;

import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    FlightDto mapFlightToDto(Flight flight){

        return FlightDto.builder()
                .date(flight.getDate())
                .hour(flight.getHour())
                .id(flight.getId())
                .pilotLicenseNumber(flight.getPilotLicenseNumber())
                .planeNumber(flight.getPlaneNumber())
                .build();
    }
}
