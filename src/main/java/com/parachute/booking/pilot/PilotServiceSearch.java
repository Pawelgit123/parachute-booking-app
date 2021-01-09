package com.parachute.booking.pilot;

import com.parachute.booking.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PilotServiceSearch {

    private final PilotRepository pilotRepository;

    List<Pilot> getAllPilots() {
        return pilotRepository.findAll();
    }

    Pilot findPilotById(Long id) {
        return pilotRepository.findById(id)
                .orElseThrow(() -> new InternalServerException("No found pilot with ID: " + id));
    }
}
