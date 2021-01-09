package com.parachute.booking.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flights")
@Validated
public class FlightController {

    private final FlightServiceCreate flightServiceCreate;
    private final FlightServiceSearch flightServiceFind;
    private final FlightServiceUpdate flightServiceUpdate;
    private final FlightServiceRemove flightServiceRemove;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDto createFlight(@RequestBody FlightDto flightDto) {

        return flightServiceCreate.createNewFlight(flightDto);
    }

    @GetMapping
    public Set<FlightDto> getAllFlights() {

        return flightServiceFind.getAllFlights();
    }

    @GetMapping("/{id}")
    public FlightDto getFlightById(@PathVariable Long id) {

        return flightServiceFind.getFlightById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFlightById(@PathVariable Long id) {

        flightServiceRemove.removeFlightById(id);
    }

    @PostMapping("/{id}/update/plane/{planeId}")
    public FlightDto updateFlightPlane(@PathVariable Long id, @PathVariable Long planeId) {


        return null;
    }

    @PostMapping("/{id}/update/pilot/{pilotId}")
    public FlightDto updateFlightPilot(@PathVariable Long id, @PathVariable Long pilotId) {


        return null;
    }

    @PostMapping("/{id}/update/hour/{hour}")
    public FlightDto updateFlightHour(@PathVariable Long id, @PathVariable Integer hour) {


        return null;
    }

    @PostMapping("/{id}/update/date/{date}")
    public FlightDto updateFlightDate(@PathVariable Long id, @PathVariable Date date) {


        return null;
    }

    @PostMapping("/{id}/{clientId}")
    public FlightDto addClientToFlight(@PathVariable Long id, @PathVariable Long clientId) {


        return null;
    }

    @DeleteMapping("/{id}/{clientId}")
    public FlightDto removeClientFromFlight(@PathVariable Long id, @PathVariable Long clientId) {


        return null;
    }

}
