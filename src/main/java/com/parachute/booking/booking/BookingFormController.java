package com.parachute.booking.booking;

import com.parachute.booking.client.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingFormController {

    private final BookingServiceSubmit bookingServiceSubmit;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String postBookingForm(@RequestBody ClientDto clientDto, LocalDateTime localDateTime) {

        bookingServiceSubmit.persistClientIfUniqueAndBookFlightByDateTIme(clientDto, localDateTime);

        return "Booking form was sent successfully.";
    }
}
