package com.parachute.booking.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FlightController {

    private final FlightServiceCreate flightServiceCreate;
    private final FlightServiceSearch flightServiceFind;
    private final FlightServiceUpdate flightServiceUpdate;
    private final FlightServiceRemove flightServiceRemove;

    private final FlightMapper flightMapper;
    private final FlightDataValidation flightDataValidation;


    @PostMapping("/flight")
    ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto){

        Flight newFlight = flightServiceCreate.createNewFlight(flightDto, flightDataValidation);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(flightMapper.mapFlightToDto(newFlight));
    }

    @GetMapping("/flight")
    Set<FlightDto> getAllFlights(){

        return flightServiceFind.getAllFlights().stream()
                .map(flightMapper::mapFlightToDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/flught/{id}")
    FlightDto getFlightById (@PathVariable Long id){

        Flight flightById = flightServiceFind.getFlightById(id);
        return flightMapper.mapFlightToDto(flightById);
    }

    @DeleteMapping("/flight/{id}")
    FlightDto removeFlightById(@PathVariable Long id){

        Flight flightById = flightServiceFind.getFlightById(id);
        FlightDto flightDto = flightMapper.mapFlightToDto(flightById);
        flightServiceRemove.removeFlightById(flightById.getId());
        return flightDto;
    }

    @PostMapping("/flight/{id}/update/plane/{planeId}")
    FlightDto updateFlightPlane(@PathVariable Long id, @PathVariable Long planeId){

        Flight flightById = flightServiceFind.getFlightById(id);

        return null;
    }

    @PostMapping("/flight/{id}/update/pilot/{pilotId}")
    FlightDto updateFlightPilot(@PathVariable Long id, @PathVariable Long pilotId){

        Flight flightById = flightServiceFind.getFlightById(id);

        return null;
    }

    @PostMapping("/flight/{id}/update/hour/{hour}")
    FlightDto updateFlightHour(@PathVariable Long id, @PathVariable Integer hour){

        Flight flightById = flightServiceFind.getFlightById(id);

        return null;
    }

    @PostMapping("/flight/{id}/update/date/{date}")
    FlightDto updateFlightDate(@PathVariable Long id, @PathVariable Date date){

        Flight flightById = flightServiceFind.getFlightById(id);

        return null;
    }

    @PostMapping("/flight/{id}/{clientId}")
    FlightDto addClientToFlight(@PathVariable Long id, @PathVariable Long clientId){

        Flight flightById = flightServiceFind.getFlightById(id);

        return null;
    }

    @DeleteMapping("/flight/{id}/{clientId}")
    FlightDto removeClientFromFlight(@PathVariable Long id,@PathVariable Long clientId){

        Flight flightById = flightServiceFind.getFlightById(id);

        return null;
    }

}
