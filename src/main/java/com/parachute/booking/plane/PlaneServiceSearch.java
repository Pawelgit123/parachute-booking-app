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
                .map(planeMapper::mapPlaneToDto)
                .collect(Collectors.toSet());
    }

    public PlaneDto findPlaneById(Long id) {
        Optional<Plane> byId = planeRepository.findById(id);
        return planeMapper.mapPlaneToDto(byId
                .orElseThrow(() -> new InternalServerException("No found plane with ID: " + id)));
    }
}
