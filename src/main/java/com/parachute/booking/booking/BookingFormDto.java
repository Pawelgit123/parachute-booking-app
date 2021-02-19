package com.parachute.booking.booking;

import com.parachute.booking.client.ClientDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingFormDto {
    private LocalDateTime plannedFlightDateTime;
    private ClientDto clientDto;

    public BookingFormDto(LocalDateTime plannedFlightDateTime, ClientDto clientDto) {
        this.plannedFlightDateTime = plannedFlightDateTime;
        this.clientDto = clientDto;
    }

    public BookingFormDto() {
    }
}
