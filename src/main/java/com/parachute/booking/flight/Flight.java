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

    private Long planeNumber;
    private Long pilotLicenseNumber;
    private Date date;
    private Integer hour;

    @OneToMany
    public Set<Client> clientSet;

}
