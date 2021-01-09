package com.parachute.booking.plane;

import org.springframework.stereotype.Component;

@Component
public class PlaneMapper {

    PlaneDto mapPlaneToDto(Plane plane){

        return PlaneDto.builder()
                .planeModel(plane.getPlaneModel())
                .planeNumber(plane.getPlaneNumber())
                .id(plane.getId())
                .build();
    }
}
