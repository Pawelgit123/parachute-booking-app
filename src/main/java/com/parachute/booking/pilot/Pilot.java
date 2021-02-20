package com.parachute.booking.pilot;

import com.parachute.booking.flight.Flight;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_Name")
    private String firstName;
    @Column(name = "surname")
    private String surName;
    @Column(name = "pilot_License_Number")
    private Long pilotLicenseNumber;

    @OneToMany(mappedBy = "pilotLicenseNumber", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<Flight> pilotFlightSet;
}
