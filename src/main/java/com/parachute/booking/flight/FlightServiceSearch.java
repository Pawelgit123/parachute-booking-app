package com.parachute.booking.flight;

import com.parachute.booking.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceSearch {

    private final FlightRepository flightRepository;

    List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    Flight getFlightById(Long id){
        return flightRepository.findById(id)
                .orElseThrow(() -> new InternalServerException("No found flight with ID: " +id));
    }

}
