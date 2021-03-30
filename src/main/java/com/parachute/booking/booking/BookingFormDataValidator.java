package com.parachute.booking.booking;

import com.parachute.booking.client.ClientDto;
import com.parachute.booking.exceptions.BadRequestException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@NoArgsConstructor
@Component
public class BookingFormDataValidator {
    public String validateBookingFormData(ClientDto clientDto, LocalDateTime localDateTime) {
        if (localDateTime == null) {
            throw new BadRequestException("Please, provide time and date of the flight.");
        }
        if (clientDto.getEmail().isBlank() || clientDto.getPesel().isBlank() || clientDto.getPhoneNumber().isBlank()
                || clientDto.getFirstName().isBlank() || clientDto.getLastName().isBlank()) {
            throw new BadRequestException("Please, provide all personal information requested in the booking form.");
        }
        return "Booking data validated successfully.";
    }
}
