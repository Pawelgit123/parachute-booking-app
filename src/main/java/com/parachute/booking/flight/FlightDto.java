package com.parachute.booking.flight;

import com.parachute.booking.pilot.Pilot;
import com.parachute.booking.plane.Plane;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private Long id;
    private Long planeNumber;
    private Long pilotLicenseNumber;
    private LocalDateTime localDateTime;
    private FlightStatus flightStatus;
}
