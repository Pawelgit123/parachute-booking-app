package com.parachute.booking.plane;

import com.parachute.booking.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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

    public Set<PlaneDto> getAllPlanes() {

        List<Plane> all = planeRepository.findAll();
        new HashSet<>(all);

        return all.stream()
                .map(planeMapper::mapPlaneDto)
                .collect(Collectors.toSet());
    }

    public PlaneDto findPlaneById(Long id) {
        Optional<Plane> byId = planeRepository.findById(id);
        return planeMapper.mapPlaneDto(byId
                .orElseThrow(() -> new InternalServerException("Not found plane with ID: " + id)));
    }

    public PlaneDto findPlaneByPlaneNumber(Long number) {
        Optional<Plane> byId = planeRepository.findByPlaneNumber(number);
        return planeMapper.mapPlaneDto(byId
                .orElseThrow(() -> new InternalServerException("Not found plane with number: " + number)));
    }

    public PlaneDto findPlaneByPlaneModel(String planeModel) {
        Optional<Plane> byId = planeRepository.findByPlaneModel(planeModel);
        return planeMapper.mapPlaneDto(byId
                .orElseThrow(() -> new InternalServerException("Not found plane by model: " + planeModel)));
    }
}
