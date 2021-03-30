package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;
import com.parachute.booking.client.ClientMapper;
import com.parachute.booking.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceDelete {

    private final BookingFormRepository bookingFormRepository;
    private final ClientMapper clientMapper;
    private final BookingFormDataValidator bookingFormDataValidator;

    public void deleteExistingBookingForm(ClientDto clientDto, LocalDateTime localDateTime) {
        bookingFormDataValidator.validateBookingFormData(clientDto, localDateTime);
        Client client = clientMapper.mapClientDto(clientDto);
        if (!bookingFormRepository.findByClientAndPlannedFlightDateTime(client, localDateTime).isEmpty()) {
            bookingFormRepository.deleteBookingFormByClientAndPlannedFlightDateTime(client, localDateTime);
        }else{
            throw new BadRequestException("Booking doesn't exist. Verify if data provided is correct.");
        }
    }
}
