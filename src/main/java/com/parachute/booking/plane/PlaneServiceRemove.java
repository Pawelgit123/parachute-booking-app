package com.parachute.booking.plane;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaneServiceRemove {

    private final PlaneRepository planeRepository;

    public void removePlaneById(Long id) {
        planeRepository.deleteById(id);
    }

}
