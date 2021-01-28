package com.parachute.booking.pilot;

import com.parachute.booking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PilotServiceRemove {

    private final PilotRepository pilotRepository;

    public void removePilotById(Long id) {

        if (pilotRepository.findById(id).isPresent()) {
            pilotRepository.deleteById(id);
        } else {
            throw new NotFoundException("Not found pilot to delete with ID: " + id);
        }
    }

}
