package com.parachute.booking.booking;

public class BookingFormMapper {
    public BookingFormDto mapBookingForm(BookingForm bookingForm){
        return BookingFormDto.builder()
                .plannedFlightDateTime(bookingForm.getPlannedFlightDateTime())
                .client(bookingForm.getClient())
                .build();
    }

    public BookingForm mapBookingFormDto(BookingFormDto bookingFormDto){
        return BookingForm.builder()
                .plannedFlightDateTime(bookingFormDto.getPlannedFlightDateTime())
                .client(bookingFormDto.getClient())
                .build();
    }
}
