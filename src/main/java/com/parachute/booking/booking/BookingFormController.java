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
    private final BookingServiceDelete bookingServiceDelete;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String postBookingForm(@RequestBody ClientDto clientDto, LocalDateTime localDateTime) {

        bookingServiceSubmit.persistClientIfUniqueAndBookFlight(clientDto, localDateTime);

        return "Booking form was sent.";
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String deleteBookingForm(@RequestBody ClientDto clientDto, LocalDateTime localDateTime) {

       bookingServiceDelete.deleteExistingBookingForm(clientDto, localDateTime);

        return "Booking form was deleted.";
    }
}
