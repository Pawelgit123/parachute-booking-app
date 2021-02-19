package com.parachute.booking.booking;

import com.parachute.booking.client.ClientDto;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingFormDto {
    private LocalDateTime plannedFlightDateTime;
    private ClientDto clientDto;
}
