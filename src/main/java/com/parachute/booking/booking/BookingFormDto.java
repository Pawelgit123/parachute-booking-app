package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingFormDto {
    private LocalDateTime plannedFlightDateTime;
    private String clientPesel;
}
