package com.parachute.booking.flight;


import com.parachute.booking.client.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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

    @Column(name = "planeNumber")
    private Long planeNumber;
    @Column(name = "pilotLicenseNumber")
    private Long pilotLicenseNumber;
    @Column(name = "date")
    private Date date;
    @Column(name = "hour")
    private Integer hour;

    @OneToMany
    public Set<Client> clientSet;

    //TODO tu będzie forecast jeszcze
    //TODO boolean flight accept?
    //TODO columns title?

}
