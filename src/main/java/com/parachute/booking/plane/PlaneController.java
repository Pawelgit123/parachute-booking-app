package com.parachute.booking.plane;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planes")
public class PlaneController {

    private final PlaneServiceSearch planeServiceSearch;
    private final PlaneServiceUpdate planeServiceUpdate;
    private final PlaneServiceCreate planeServiceCreate;
    private final PlaneServiceRemove planeServiceRemove;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public PlaneDto createPlane(@RequestBody PlaneDto planeDto) {

        return planeServiceCreate.createNewPlane(planeDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PlaneDtoListed getAllPlanes() {

        return planeServiceSearch.getAllPlanes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlaneDto getPlanetById(@PathVariable Long id) {

        return planeServiceSearch.findPlaneById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public void removePlaneById(@PathVariable Long id) {

        planeServiceRemove.removePlaneById(id);
    }

    @GetMapping("/number/{number}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public PlaneDto getPlanetByNumber(@PathVariable Long number) {

        return planeServiceSearch.findPlaneByPlaneNumber(number);
    }

    @GetMapping("/model/{model}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole(T(com.parachute.booking.security.Roles).ADMIN.toString())")
    public PlaneDto getPlanetByModel(@PathVariable String model) {

        return planeServiceSearch.findPlaneByPlaneModel(model);
    }


}
