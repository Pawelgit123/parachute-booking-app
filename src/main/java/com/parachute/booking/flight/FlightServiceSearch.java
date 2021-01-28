package com.parachute.booking.flight;

import com.parachute.booking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightServiceSearch {

    private final FlightRepository flightRepository;
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

    public FlightDto getFlightByPlaneNumber(Long plane) {
        Optional<Flight> byId = flightRepository.findByPlaneNumber(plane);

        return flightMapper.mapFlightDto(byId
                .orElseThrow(() -> new NotFoundException("Not found flight with Plane Number: " + plane)));
    }

    public FlightDto getFlightByPilotLicenseNumber(Long pilot) {
        Optional<Flight> byId = flightRepository.findByPilotLicenseNumber(pilot);

        return flightMapper.mapFlightDto(byId
                .orElseThrow(() -> new NotFoundException("Not found flight with Pilot License Number: " + pilot)));
    }

}
