package com.parachute.booking.flight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private Long id;
    private Long planeNumber;
    private Long pilotLicenseNumber;
    private Date date;
    private Integer hour;

}

/*
    private Long id;                    // 1
    private Long planeNumber;           // 123
    private Long pilotLicenseNumber;    // 321
    private Date date;                  // 1.1.2000 // LocalDateTime
    private Integer hour;               // 14:00
 */

// 1. front pobiera flight o id 1 -> wszystkie
// 2. admin poprawie godzinę na 15

/*
    PUT:
    private Long id;                    // 1
    private Long planeNumber;           // 123
    private Long pilotLicenseNumber;    // 321
    private Date date;                  // 1.1.2000 // LocalDateTime
    private Integer hour;               // 15:00
 */

// BACKEND
/*
    private Long id;                    // 1       -> 1
    private Long planeNumber;           // 123     -> 123
    private Long pilotLicenseNumber;    // 321     -> 321
    private Date date;                  // 1.1.2000 // LocalDateTime -?> 1.1.2000
    private Integer hour;               // 14:00   -> 15:00
 */

