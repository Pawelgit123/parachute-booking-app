package com.parachute.booking.flight;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "planeNumber")
    private Long planeNumber;
    @Column(name = "pilotLicenseNumber")
    private Long pilotLicenseNumber;

    @Column(name = "localdatetime")
    private LocalDateTime localDateTime;
    @Column(name = "status")
    private FlightStatus flightStatus;

    //TODO tu będzie forecast jeszcze

}
