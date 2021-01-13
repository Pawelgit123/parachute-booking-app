package com.parachute.booking.pilot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
    public PilotDto createPilot(@RequestBody PilotDto pilotDto) {

        return pilotServiceCreate.createNewPilot(pilotDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<PilotDto> getAllPilots() {

        return pilotServiceSearch.getAllPilots();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PilotDto getPilotById(@PathVariable Long id) {

        return pilotServiceSearch.findPilotById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePilotById(@PathVariable Long id) {

        pilotServiceRemove.removePilotById(id);
    }

    @GetMapping("/license/{licenseNumber}")
    @ResponseStatus(HttpStatus.OK)
    public PilotDto getPilotByLicense(@PathVariable Long licenseNumber) {

        return pilotServiceSearch.findPilotByLicenseNumber(licenseNumber);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public PilotDto getPilotByName(@PathVariable String name) {

        return pilotServiceSearch.findPilotByFirstName(name);
    }

    @GetMapping("/surname/{surname}")
    @ResponseStatus(HttpStatus.OK)
    public PilotDto getPilotBySurName(@PathVariable String surname) {

        return pilotServiceSearch.findPilotBySurNamee(surname);
    }

}
