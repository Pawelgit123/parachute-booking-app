package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;
import com.parachute.booking.client.ClientMapper;
import com.parachute.booking.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookingServiceSubmitTest {

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private BookingFormRepository bookingFormRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private BookingFormDataValidator bookingFormDataValidator;

    @InjectMocks
    private BookingServiceSubmit bookingServiceSubmit;

    @BeforeEach
    void setup() {
        bookingFormRepository.deleteAll();
        clientRepository.deleteAll();
    }

    private ClientDto createClientDtoForTest() {
        return ClientDto.builder()
                .firstName("Bob")
                .lastName("Skywalker")
                .pesel("90023008655")
                .email("Bobski@gmail.com")
                .phoneNumber("+48 943 007 420")
                .build();
    }

    private LocalDateTime createLocalDateTimeExample() {
        return LocalDateTime.now();
    }

    @Test
    void persistClientIfUniqueAndBookFlight_persistsNewClientTest() {
        //when
        bookingServiceSubmit.persistClientIfUniqueAndBookFlight(createClientDtoForTest(), createLocalDateTimeExample());
        //then
        verify(clientRepository).save(clientMapper.mapClientDto(createClientDtoForTest()));
        verify(bookingFormRepository).save(any(BookingForm.class));
    }

    @Test
    void persistClientIfUniqueAndBookFlight_doesntPersistExistingClientTest() {
        //given
        clientRepository.save(clientMapper.mapClientDto(createClientDtoForTest()));
        //when
        bookingServiceSubmit.persistClientIfUniqueAndBookFlight(createClientDtoForTest(), createLocalDateTimeExample());
        //then
        verify(clientRepository, times(0)).save(any(Client.class));
        verify(bookingFormRepository).save(any(BookingForm.class));
    }
}
