package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;
import com.parachute.booking.client.ClientMapper;
import com.parachute.booking.flight.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceDeleteTest {

    @Mock
    private Flight flight;

    @Mock
    private BookingFormRepository bookingFormRepository;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private BookingFormDataValidator bookingFormDataValidator;

    @InjectMocks
    private BookingServiceDelete bookingServiceDelete;

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
        return LocalDateTime.of(2020, Month.JUNE, 15, 13, 39);
    }

    private BookingForm createBookingFormForTest(){
        ClientMapper clientMapper = new ClientMapper();
        return BookingForm.builder()
                .client(clientMapper.mapClientDto(createClientDtoForTest()))
                .plannedFlightDateTime(createLocalDateTimeExample())
                .flight(flight)
                .build();
    }

    @BeforeEach
    void setup() {
        bookingFormRepository.deleteAll();
    }

    //TODO - doesn't work properly
//    @Test
//    void deleteExistingBookingForm() {
//        //given
//        ClientMapper clientMapper = new ClientMapper();
//        bookingFormRepository.save(createBookingFormForTest());
//        //when
//        bookingServiceDelete.deleteExistingBookingForm(createClientDtoForTest(), createLocalDateTimeExample());
//        //then
////        verify(bookingFormRepository).delete(any(BookingForm.class));
//        verify(bookingFormRepository).deleteBookingFormByClientAndPlannedFlightDateTime(clientMapper.mapClientDto(createClientDtoForTest()), createLocalDateTimeExample());
//    }
}
