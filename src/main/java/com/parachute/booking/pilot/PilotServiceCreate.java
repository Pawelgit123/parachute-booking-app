package com.parachute.booking.pilot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PilotServiceCreate {

    private final PilotRepository pilotRepository;

    public PilotDto createNewPilot(PilotDto pilotDto) {

        Pilot pilot = new Pilot();

        pilot.setFirstName(pilotDto.getFirstName());
        pilot.setSurName(pilotDto.getSurName());
        pilot.setPilotLicenseNumber(pilotDto.getPilotLicenseNumber());

        pilotRepository.save(pilot);

        return pilotDto;
    }
}
