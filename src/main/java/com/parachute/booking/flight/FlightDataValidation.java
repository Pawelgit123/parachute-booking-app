package com.parachute.booking.flight;

import com.parachute.booking.exceptions.InternalServerException;
import org.springframework.stereotype.Component;

@Component
public class FlightDataValidation {

    void validateFlightData(FlightDto flightDto){

        if(flightDto == null){
            throw new InternalServerException("No data to create Flight");
        }




    }
}
