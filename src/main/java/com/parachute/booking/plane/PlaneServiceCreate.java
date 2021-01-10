package com.parachute.booking.plane;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaneServiceCreate {

    private final PlaneRepository planeRepository;

    public PlaneDto createNewPlane(PlaneDto planeDto) {

        Plane plane = new Plane();

        plane.setPlaneModel(planeDto.getPlaneModel());
        plane.setPlaneNumber(planeDto.getPlaneNumber());

        planeRepository.save(plane);

        return planeDto;
    }
}
