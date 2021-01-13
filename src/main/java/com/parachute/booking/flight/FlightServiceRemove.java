package com.parachute.booking.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightServiceRemove {

    private final FlightRepository flightRepository;

    public void removeFlightById(Long id) {
        flightRepository.deleteById(id);
    }
}
