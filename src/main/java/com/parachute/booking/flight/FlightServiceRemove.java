package com.parachute.booking.flight;

import com.parachute.booking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightServiceRemove {

    private final FlightRepository flightRepository;

    public void removeFlightById(Long id) {

        if (flightRepository.findById(id).isPresent()) {
            flightRepository.deleteById(id);
        } else {
            throw new NotFoundException("Not found flight to delete with ID: " + id);
        }
    }
}
