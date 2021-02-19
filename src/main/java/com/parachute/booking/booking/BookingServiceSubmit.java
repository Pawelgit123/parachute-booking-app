package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;
import com.parachute.booking.client.ClientMapper;
import com.parachute.booking.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceSubmit {

    private final BookingFormRepository bookingFormRepository;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public void persistClientIfUniqueAndBookFlightByDateTIme(ClientDto clientDto, LocalDateTime localDateTime) {

        if (!clientRepository.findAllEmails().contains(clientDto.getEmail())
                && !clientRepository.findAllPesels().contains(clientDto.getPesel())
                && !clientRepository.findAllPhoneNumbers().contains(clientDto.getPhoneNumber())) {

            final Client client = clientMapper.mapClientDto(clientDto);
            clientRepository.save(client);
        }
        BookingForm bookingForm = BookingForm.builder()
                .plannedFlightDateTime(localDateTime)
                .clientPesel(clientDto.getPesel()).build();
        bookingFormRepository.save(bookingForm);
    }
}
