package com.parachute.booking.plane;

import com.parachute.booking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaneServiceSearch {

    private final PlaneRepository planeRepository;
    private final PlaneMapper planeMapper;

    public PlaneDtoListed getAllPlanes() {

        List<Plane> all = planeRepository.findAll();
        PlaneDtoListed planeDtoListed = new PlaneDtoListed();

        Set<PlaneDto> collect = all.stream().map(planeMapper::mapPlaneDto).collect(Collectors.toSet());
        planeDtoListed.setPlanes(collect);

        return planeDtoListed;
    }

    public PlaneDto findPlaneById(Long id) {
        Optional<Plane> byId = planeRepository.findById(id);
        return planeMapper.mapPlaneDto(byId
                .orElseThrow(() -> new NotFoundException("Not found plane with ID: " + id)));
    }

    public PlaneDto findPlaneByPlaneNumber(Long number) {
        Optional<Plane> byId = planeRepository.findByPlaneNumber(number);
        return planeMapper.mapPlaneDto(byId
                .orElseThrow(() -> new NotFoundException("Not found plane with number: " + number)));
    }

    public PlaneDto findPlaneByPlaneModel(String planeModel) {
        Optional<Plane> byId = planeRepository.findByPlaneModel(planeModel);
        return planeMapper.mapPlaneDto(byId
                .orElseThrow(() -> new NotFoundException("Not found plane by model: " + planeModel)));
    }
}
