package com.parachute.booking.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flights")
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
    @ResponseStatus(HttpStatus.OK)
    public Set<FlightDto> getAllFlights() {

        return flightServiceFind.getAllFlights();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDto getFlightById(@PathVariable Long id) {

        return flightServiceFind.getFlightById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFlightById(@PathVariable Long id) {

        flightServiceRemove.removeFlightById(id);
    }

    @GetMapping("/plane/{plane}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDto getFlightByPlane(@PathVariable Long plane) {

        return flightServiceFind.getFlightByPlaneNumber(plane);
    }

    @GetMapping("/pilot/{pilot}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDto getFlightByPilotLicense(@PathVariable Long pilot) {

        return flightServiceFind.getFlightByPilotLicenseNumber(pilot);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFlightById(@Valid @RequestBody FlightDto flightDto, @PathVariable Long id) {

        flightServiceUpdate.updateFlightById(flightDto, id);
    }

//    @PatchMapping("/{id}")
//    public void updatePariallyFlightById(@RequestBody FlightDto flightDto, @PathVariable Long id) {
//
//        flightServiceUpdate.updateFlightById(flightDto, id);
//    }

//    @PostMapping("/{id}/{clientId}")
//    @ResponseStatus(HttpStatus.OK)
//    public FlightDto addClientToFlight(@PathVariable Long id, @PathVariable Long clientId) {
//
//
//        return null;
//    }
//
//    @DeleteMapping("/{id}/{clientId}")
//    @ResponseStatus(HttpStatus.OK)
//    public FlightDto removeClientFromFlight(@PathVariable Long id, @PathVariable Long clientId) {
//
//
//        return null;
//    }

}
