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

@Entity(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
//    @NotBlank(message = "Date is mandatory") TODO <- confirm
    private LocalDateTime plannedFlightDateTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "client")
    private Client client;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "flight")
    private Flight flight;
}
