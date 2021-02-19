package com.parachute.booking.booking;

import com.parachute.booking.client.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingFormMapper {

    private final ClientMapper clientmapper;

    public BookingFormDto mapBookingForm(BookingForm bookingForm) {
        return BookingFormDto.builder()
                .plannedFlightDateTime(bookingForm.getPlannedFlightDateTime())
                .clientDto(clientmapper.mapClient(bookingForm.getClient()))
                .build();
    }

    public BookingForm mapBookingFormDto(BookingFormDto bookingFormDto) {
        return BookingForm.builder()
                .plannedFlightDateTime(bookingFormDto.getPlannedFlightDateTime())
                .client(clientmapper.mapClientDto(bookingFormDto.getClientDto()))
                .build();
    }
}
