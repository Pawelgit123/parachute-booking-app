package com.parachute.booking.plane;

import com.parachute.booking.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaneServiceCreate {

    private final PlaneRepository planeRepository;
    private final PlaneMapper planeMapper;

    public PlaneDto createNewPlane(PlaneDto planeDto) {

        if (planeDto == null) {
            throw new InternalServerException("No data to create Plane");
        }

        Plane plane = new Plane();

        plane.setPlaneModel(planeDto.getPlaneModel());
        plane.setPlaneNumber(planeDto.getPlaneNumber());

        planeRepository.save(plane);

        return planeMapper.mapPlaneDto(plane);
    }
}
