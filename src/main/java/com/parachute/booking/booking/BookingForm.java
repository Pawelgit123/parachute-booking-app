package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import com.parachute.booking.flight.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "Date is mandatory")
    private LocalDateTime plannedFlightDateTime;
    @ManyToOne
    @JoinColumn(columnDefinition = "client")
    private Client client;
    @ManyToOne
    @JoinColumn(columnDefinition = "flight")
    private Flight flight;

//1-sided relationship with Flight
//2-sided relationship with Client
}
