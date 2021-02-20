package com.parachute.booking.flight;

import com.parachute.booking.exceptions.InternalServerException;
import com.parachute.booking.pilot.Pilot;
import com.parachute.booking.pilot.PilotRepository;
import com.parachute.booking.plane.Plane;
import com.parachute.booking.plane.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightServiceCreate {

    private final FlightRepository flightRepository;
    private final PlaneRepository planeRepository;
    private final PilotRepository pilotRepository;
    private final FlightMapper flightMapper;

    public FlightDto createNewFlight(FlightDto flightDto) {

        if(flightDto==null){
            throw new InternalServerException("No Data to create flight");
        }

        Optional<Plane> byPlaneNumber = planeRepository.findByPlaneNumber(flightDto.getPlaneNumber());
        Optional<Pilot> byPilotLicenseNumber = pilotRepository.findByPilotLicenseNumber(flightDto.getPilotLicenseNumber());

        if(byPlaneNumber.isPresent()&&byPilotLicenseNumber.isPresent()){
//            final Flight flight = flightMapper.mapFlight(flightDto);
            Flight flight = new Flight();
            flight.setFlightStatus(FlightStatus.FLIGHT_CREATED);
            flight.setLocalDateTime(flightDto.getLocalDateTime());
            flight.setPlaneNumber(byPlaneNumber.get());
            flight.setPilotLicenseNumber(byPilotLicenseNumber.get());

            Set<Flight> planeFlightSet = byPlaneNumber.get().getPlaneFlightSet();
            planeFlightSet.add(flight);

            Set<Flight> pilotFlightSet = byPilotLicenseNumber.get().getPilotFlightSet();
            pilotFlightSet.add(flight);

            flightRepository.save(flight);
            return flightMapper.mapFlightDto(flight);

        } else {
            throw new InternalServerException("No data to create Flight");
        }
    }
}
