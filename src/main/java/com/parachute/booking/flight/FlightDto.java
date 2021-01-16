package com.parachute.booking.flight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private Long id;
    @NotNull
    private Long planeNumber;
    @NotNull
    private Long pilotLicenseNumber;
    @NotNull
    private LocalDateTime localDateTime;

}
