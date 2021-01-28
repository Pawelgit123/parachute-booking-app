package com.parachute.booking.plane;

import org.springframework.stereotype.Component;

@Component
public class PlaneMapper {

    public PlaneDto mapPlaneDto(Plane plane) {

        return PlaneDto.builder()
                .planeModel(plane.getPlaneModel())
                .planeNumber(plane.getPlaneNumber())
                .id(plane.getId())
                .build();
    }

    public Plane mapPlane(PlaneDto planeDto) {

        return Plane.builder()
                .planeModel(planeDto.getPlaneModel())
                .planeNumber(planeDto.getPlaneNumber())
                .id(planeDto.getId())
                .build();
    }
}
