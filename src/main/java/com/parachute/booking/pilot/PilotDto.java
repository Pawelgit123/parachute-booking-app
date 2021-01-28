package com.parachute.booking.pilot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PilotDto {

    private Long id;
    private String firstName;
    private String surName;
    private Long pilotLicenseNumber;
}
