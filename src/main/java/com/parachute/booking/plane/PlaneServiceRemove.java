package com.parachute.booking.plane;

import com.parachute.booking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaneServiceRemove {

    private final PlaneRepository planeRepository;

    public void removePlaneById(Long id) {

        if (planeRepository.findById(id).isPresent()) {

            planeRepository.deleteById(id);
        } else {
            throw new NotFoundException("Not found plane to delete with ID: " + id);
        }
    }

}
