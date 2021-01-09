package com.parachute.booking.pilot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PilotServiceRemove {

    private final PilotRepository pilotRepository;

    void removePilotById(Long id) {
        pilotRepository.deleteById(id);
    }
}
