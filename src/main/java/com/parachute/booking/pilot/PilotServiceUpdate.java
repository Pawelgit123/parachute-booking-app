package com.parachute.booking.pilot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PilotServiceUpdate {

    private final PilotRepository pilotRepository;
    private final PilotMapper pilotMapper;

}
