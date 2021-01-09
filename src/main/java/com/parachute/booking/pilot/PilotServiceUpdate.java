package com.parachute.booking.pilot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PilotServiceUpdate {

    private final PilotRepository pilotRepository;
}
