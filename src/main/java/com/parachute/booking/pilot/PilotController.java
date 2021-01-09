package com.parachute.booking.pilot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PilotController {

    private final PilotServiceCreate pilotServiceCreate;
    private final PilotServiceRemove pilotServiceRemove;
    private final PilotServiceSearch pilotServiceSearch;
    private final PilotServiceUpdate pilotServiceUpdate;

    private final PilotMapper pilotMapper;

}
