package com.parachute.booking.pilot;

import com.parachute.booking.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PilotServiceCreate {

    private final PilotRepository pilotRepository;
    private final PilotMapper pilotMapper;

    public PilotDto createNewPilot(PilotDto pilotDto) {

        if (pilotDto == null) {
            throw new InternalServerException("No data to create Pilot");
        }

        final Pilot pilot = pilotMapper.mapPilot(pilotDto);

        pilotRepository.save(pilot);

        return pilotMapper.mapPilotDto(pilot);
    }
}
