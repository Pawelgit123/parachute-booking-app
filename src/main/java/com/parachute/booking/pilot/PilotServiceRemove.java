package com.parachute.booking.pilot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PilotServiceRemove {

    private final PilotRepository pilotRepository;

    public void removePilotById(Long id) {
        pilotRepository.deleteById(id);
    }
}
