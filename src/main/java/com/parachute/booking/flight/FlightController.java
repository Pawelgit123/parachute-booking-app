package com.parachute.booking.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


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
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public FlightDto createFlight(@RequestBody FlightDto flightDto) {

        return flightServiceCreate.createNewFlight(flightDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public FlightDtoListed getAllFlights() {

        return flightServiceFind.getAllFlights();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDto getFlightById(@PathVariable Long id) {

        return flightServiceFind.getFlightById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
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
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public void updateFlightById(@RequestBody FlightDto flightDto, @PathVariable Long id) {

        flightServiceUpdate.patchFlightById(flightDto, id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public void updated(@RequestBody FlightDto flightDto, @PathVariable Long id) {

        flightServiceUpdate.updateFlightById(flightDto,id);
    }

}
