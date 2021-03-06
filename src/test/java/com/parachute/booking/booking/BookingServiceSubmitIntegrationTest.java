package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;
import com.parachute.booking.client.ClientMapper;
import com.parachute.booking.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
//TODO
@SpringBootTest
@AutoConfigureMockMvc
class BookingServiceSubmitIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookingFormRepository bookingFormRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    BookingFormDataValidator bookingFormDataValidator;

    @BeforeEach
    void setUp() {
        bookingFormRepository.deleteAll();
    }

    BookingServiceSubmit bookingServiceSubmit
            = new BookingServiceSubmit(bookingFormRepository, clientRepository, clientMapper, bookingFormDataValidator);

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

    private BookingForm createBookingFormForTest() {
        ClientMapper clientMapper = new ClientMapper();
        return BookingForm.builder()
                .client(clientMapper.mapClientDto(createClientDtoForTest()))
                .plannedFlightDateTime(createLocalDateTimeExample())
                .build();
    }

    @Test
    void persistClientIfUniqueAndBookFlight_successTest() throws Exception{
        //given

        //when
        bookingServiceSubmit.persistClientIfUniqueAndBookFlight(createClientDtoForTest(), createLocalDateTimeExample());
//        //then
//        verify(clientRepository, times(1)).save(any(Client.class));
//        verify(bookingFormRepository, times(1)).save(any(BookingForm.class));
//        assertThat(clientMapper.mapClient(clientRepository.findAll().get(0))).isEqualTo(createClientDtoForTest());
    }
}
