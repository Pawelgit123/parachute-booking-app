package com.parachute.booking.flight;

import com.parachute.booking.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightServiceCreate {

    private final FlightRepository flightRepository;

    FlightDto createNewFlight(FlightDto flightDto) {

        if (flightDto == null) {
            throw new InternalServerException("No data to create Flight");
        }

        Flight flight = new Flight();

        flight.setDate(flightDto.getDate());
        flight.setHour(flightDto.getHour());
        flight.setPilotLicenseNumber(flightDto.getPilotLicenseNumber());
        flight.setPlaneNumber(flightDto.getPlaneNumber());

        //TODO czy nie powinien robić pustego flightu a potem Admin robi mu update??

        flightRepository.save(flight);

        return flightDto;

    }
}
