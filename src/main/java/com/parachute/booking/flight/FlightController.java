package com.parachute.booking.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FlightController {

    private final FlightServiceCreate flightServiceCreate;
    private final FlightServiceGetAll flightServiceGetAll;
    private final FlightServiceFind flightServiceFind;
    private final FlightServiceUpdate flightServiceUpdate;
    private final FlightServiceRemove flightServiceRemove;

}
