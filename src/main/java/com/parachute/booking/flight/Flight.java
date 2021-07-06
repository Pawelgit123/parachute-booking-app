package com.parachute.booking.flight;


import com.parachute.booking.booking.BookingForm;
import com.parachute.booking.pilot.Pilot;
import com.parachute.booking.plane.Plane;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(columnDefinition = "plane_Assigned")
    private Plane planeNumber;
    @ManyToOne
    @JoinColumn(columnDefinition = "pilot_Assigned")
    private Pilot pilotLicenseNumber;
    @OneToMany(mappedBy = "flight",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<BookingForm> bookingFormSet;

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;
    @Column(name = "status")
    private FlightStatus flightStatus;


}
