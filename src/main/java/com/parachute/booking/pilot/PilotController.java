package com.parachute.booking.pilot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pilots")
public class PilotController {

    private final PilotServiceCreate pilotServiceCreate;
    private final PilotServiceRemove pilotServiceRemove;
    private final PilotServiceSearch pilotServiceSearch;
    private final PilotServiceUpdate pilotServiceUpdate;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public PilotDto createPilot(@RequestBody PilotDto pilotDto) {

        return pilotServiceCreate.createNewPilot(pilotDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PilotDtoListed getAllPilots() {

        return pilotServiceSearch.getAllPilots();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public PilotDto getPilotById(@PathVariable Long id) {

        return pilotServiceSearch.findPilotById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public void removePilotById(@PathVariable Long id) {

        pilotServiceRemove.removePilotById(id);
    }

    @GetMapping("/license/{licenseNumber}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public PilotDto getPilotByLicense(@PathVariable Long licenseNumber) {

        return pilotServiceSearch.findPilotByLicenseNumber(licenseNumber);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public PilotDto getPilotByName(@PathVariable String name) {

        return pilotServiceSearch.findPilotByFirstName(name);
    }

    @GetMapping("/surname/{surname}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public PilotDto getPilotBySurName(@PathVariable String surname) {

        return pilotServiceSearch.findPilotBySurName(surname);
    }

}
