package com.parachute.booking.plane;

import com.parachute.booking.flight.Flight;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "plane_Number")
    private Long planeNumber;
    @Column(name = "plane_Model")
    private String planeModel;
    @Column(name = "seats")
    private Integer seats;

    @OneToMany(mappedBy = "planeNumber", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<Flight> planeFlightSet;

}
