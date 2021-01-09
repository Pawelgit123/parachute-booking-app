package com.parachute.booking.plane;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneMapper planeMapper;
}
