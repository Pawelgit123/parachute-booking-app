package com.parachute.booking.pilot;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pilots")
@Validated
public class PilotController {

    private final PilotServiceCreate pilotServiceCreate;
    private final PilotServiceRemove pilotServiceRemove;
    private final PilotServiceSearch pilotServiceSearch;
    private final PilotServiceUpdate pilotServiceUpdate;

}
