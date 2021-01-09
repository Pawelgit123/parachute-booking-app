package com.parachute.booking.pilot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PilotServiceCreate {

    private final PilotRepository pilotRepository;

    Pilot createNewPilot(PilotDto pilotDto) {

        Pilot pilot = new Pilot();

        pilot.setFirstName(pilotDto.getFirstName());
        pilot.setSurName(pilotDto.getSurName());
        pilot.setPilotLicenseNumber(pilotDto.getPilotLicenseNumber());

        return pilotRepository.save(pilot);
    }
}
