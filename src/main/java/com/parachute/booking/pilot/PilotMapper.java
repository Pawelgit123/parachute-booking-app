package com.parachute.booking.pilot;

import org.springframework.stereotype.Component;

@Component
public class PilotMapper {

    public PilotDto mapPilotDto(Pilot pilot) {

        return PilotDto.builder()
                .firstName(pilot.getFirstName())
                .surName(pilot.getSurName())
                .pilotLicenseNumber(pilot.getPilotLicenseNumber())
                .id(pilot.getId())
                .build();
    }

    public Pilot mapPilot(PilotDto pilotDto) {

        return Pilot.builder()
                .firstName(pilotDto.getFirstName())
                .surName(pilotDto.getSurName())
                .pilotLicenseNumber(pilotDto.getPilotLicenseNumber())
                .id(pilotDto.getId())
                .build();
    }
}
