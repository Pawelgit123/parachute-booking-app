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
    private final FlightMapper flightMapper;

    public FlightDto createNewFlight(FlightDto flightDto) {

        if (flightDto == null) {
            throw new InternalServerException("No data to create Flight");
        }

        final Flight flight = flightMapper.mapFlight(flightDto);
        flight.setFlightStatus(FlightStatus.FLIGHT_CREATED);

        flightRepository.save(flight);

        return flightMapper.mapFlightDto(flight);

    }
}
