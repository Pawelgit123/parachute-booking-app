package com.parachute.booking.flight;

import com.parachute.booking.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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

    public Set<FlightDto> getAllFlights() {

        List<Flight> all = flightRepository.findAll();
        new HashSet<>(all);

        return all.stream()
                .map(flightMapper::mapFlightToDto)
                .collect(Collectors.toSet());
    }

    FlightDto getFlightById(Long id) {
        Optional<Flight> byId = flightRepository.findById(id);

        return flightMapper.mapFlightToDto(byId
                .orElseThrow(() -> new InternalServerException("No found flight with ID: " + id)));
    }

}
