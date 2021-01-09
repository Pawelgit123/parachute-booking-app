package com.parachute.booking.pilot;

import org.springframework.stereotype.Component;

@Component
public class PilotMapper {

    PilotDto mapPilotToDto(Pilot pilot){

        return PilotDto.builder()
                .firstName(pilot.getFirstName())
                .surName(pilot.getSurName())
                .pilotLicenseNumber(pilot.getPilotLicenseNumber())
                .id(pilot.getId())
                .build();
    }
}
