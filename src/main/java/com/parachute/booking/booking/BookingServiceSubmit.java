package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;
import com.parachute.booking.client.ClientMapper;
import com.parachute.booking.client.ClientRepository;
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
    private final BookingFormMapper bookingFormMapper;
    private final ClientMapper clientMapper;

    public void persistClientIfUniqueAndBookFlightByDateTIme(ClientDto clientDto, LocalDateTime localDateTime){


    }

}
