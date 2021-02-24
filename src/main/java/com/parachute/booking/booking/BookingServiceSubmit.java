package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;
import com.parachute.booking.client.ClientMapper;
import com.parachute.booking.client.ClientRepository;
import com.parachute.booking.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceSubmit {

    private final BookingFormRepository bookingFormRepository;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final BookingFormDataValidator bookingFormDataValidator;

    public void persistClientIfUniqueAndBookFlight(ClientDto clientDto, LocalDateTime localDateTime){
        bookingFormDataValidator.validateBookingFormData(clientDto, localDateTime);

        if (!clientRepository.findAllEmails().contains(clientDto.getEmail())
                && !clientRepository.findAllPesels().contains(clientDto.getPesel())
                && !clientRepository.findAllPhoneNumbers().contains(clientDto.getPhoneNumber())) {

            final Client client = clientMapper.mapClientDto(clientDto);
            clientRepository.save(client);
        }
        BookingForm bookingForm = BookingForm.builder()
                .plannedFlightDateTime(localDateTime)
                .client(clientMapper.mapClientDto(clientDto)).build();
        bookingFormRepository.save(bookingForm);
    }
}
