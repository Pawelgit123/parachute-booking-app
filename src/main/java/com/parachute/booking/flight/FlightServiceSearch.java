package com.parachute.booking.flight;

import com.parachute.booking.exceptions.NotFoundException;
import com.parachute.booking.pilot.Pilot;
import com.parachute.booking.pilot.PilotRepository;
import com.parachute.booking.plane.Plane;
import com.parachute.booking.plane.PlaneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FlightServiceSearch {

    private final FlightRepository flightRepository;
    private final PlaneRepository planeRepository;
    private final PilotRepository pilotRepository;
    private final FlightMapper flightMapper;

    public FlightDtoListed getAllFlights() {

        List<Flight> all = flightRepository.findAll();
        FlightDtoListed flightDtoListed = new FlightDtoListed();

        Set<FlightDto> collect = all.stream().map(flightMapper::mapFlightDto).collect(Collectors.toSet());
        flightDtoListed.setFlights(collect);

        return flightDtoListed;
    }

    public FlightDto getFlightById(Long id) {
        Optional<Flight> byId = flightRepository.findById(id);

        return flightMapper.mapFlightDto(byId
                .orElseThrow(() -> new NotFoundException("Not found flight with ID: " + id)));
    }

    public FlightDtoListed getAllFlightsByPlaneNumber(Long planeNumberToFind) {

        Optional<Plane> byPlaneNumber = planeRepository.findByPlaneNumber(planeNumberToFind);
        if (byPlaneNumber.isPresent()) {
            Set<Flight> planeFlightSet = byPlaneNumber.get().getPlaneFlightSet();
            FlightDtoListed flightDtoListed = new FlightDtoListed();

            Set<FlightDto> collect = planeFlightSet.stream().map(flightMapper::mapFlightDto).collect(Collectors.toSet());
            flightDtoListed.setFlights(collect);

            return flightDtoListed;
        } else {
            throw new NotFoundException("Not found flights with Plane Number: " + planeNumberToFind);
        }
    }

    public FlightDtoListed getFlightByPilotLicenseNumber(Long pilotNumberToFind) {

        Optional<Pilot> byPilotLicenseNumber = pilotRepository.findByPilotLicenseNumber(pilotNumberToFind);
        if (byPilotLicenseNumber.isPresent()) {
            Set<Flight> planeFlightSet = byPilotLicenseNumber.get().getPilotFlightSet();
            FlightDtoListed flightDtoListed = new FlightDtoListed();

            Set<FlightDto> collect = planeFlightSet.stream().map(flightMapper::mapFlightDto).collect(Collectors.toSet());
            flightDtoListed.setFlights(collect);

            return flightDtoListed;
        } else {
            throw new NotFoundException("Not found flights with Pilot License Number: " + pilotNumberToFind);
        }
    }

}


