package com.parachute.booking.flight;

import com.parachute.booking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceUpdate {

    private final FlightRepository flightRepository;
    //TODO block update for finished flights?

    public void updateFlightById(FlightDto flightDto, Long id) {

        Optional<Flight> byId = flightRepository.findById(id);

        if (byId.isPresent()) {
            Flight flight = new Flight();
            if (flightDto.getPlaneNumber() != null) {
                flight.setPlaneNumber(flightDto.getPlaneNumber());
            }
            if (flightDto.getPilotLicenseNumber() != null) {
                flight.setPilotLicenseNumber(flightDto.getPilotLicenseNumber());
            }
            if (flightDto.getLocalDateTime() != null) {
                flight.setLocalDateTime(flightDto.getLocalDateTime());
            }
            flight.setId(id);

            flightRepository.save(flight);

        } else {
            throw new NotFoundException("Not found flight to update with ID: " + id);
        }
    }
}
