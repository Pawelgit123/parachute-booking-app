package com.parachute.booking.plane;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "planeNumber")
    private Long planeNumber;
    @Column(name = "planeModel")
    private String planeModel;
    @Column(name = "seats")
    private int seats;

}
