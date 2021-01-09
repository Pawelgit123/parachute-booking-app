package com.parachute.booking.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceCreate {

    private final FlightRepository flightRepository;

    Flight createNewFlight (FlightDto flightDto, FlightDataValidation flightDataValidation){

        flightDataValidation.validateFlightData(flightDto);

        Flight flight = new Flight();

        flight.setDate(flightDto.getDate());
        flight.setHour(flightDto.getHour());
        flight.setPilotLicenseNumber(flightDto.getPilotLicenseNumber());
        flight.setPlaneNumber(flightDto.getPlaneNumber());

        //TODO czy nie powinien robić pustego flightu a potem Admin robi mu update??

        return flightRepository.save(flight);

    }
}
