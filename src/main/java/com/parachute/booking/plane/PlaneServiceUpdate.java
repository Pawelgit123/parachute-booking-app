package com.parachute.booking.plane;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaneServiceUpdate {

    private final PlaneRepository planeRepository;
    private final PlaneMapper planeMapper;
}
