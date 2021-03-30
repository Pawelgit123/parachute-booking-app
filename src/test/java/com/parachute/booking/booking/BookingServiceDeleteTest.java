package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;
import com.parachute.booking.client.ClientMapper;
import com.parachute.booking.exceptions.BadRequestException;
import com.parachute.booking.exceptions.NotFoundException;
import com.parachute.booking.flight.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceDeleteTest {

    @Spy
    private BookingFormRepository bookingFormRepository;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private BookingFormDataValidator bookingFormDataValidator;

    @InjectMocks
    private BookingServiceDelete bookingServiceDelete;

    private Client createClientForTest() {
        Client bob = Client.builder()
                .firstName("Bob")
                .lastName("Skywalker")
                .pesel("90023008655")
                .email("Bobski@gmail.com")
                .phoneNumber("+48 943 007 420")
                .build();
        return bob;
    }

    private Client createWrongClientForTest(){
        return Client.builder()
                .firstName("Ben")
                .lastName("Solo")
                .pesel("19023008655")
                .email("Bensol@gmail.com")
                .phoneNumber("+48 947 007 420")
                .build();
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

    private ClientDto createWrongClientDtoForTest() {
        return ClientDto.builder()
                .firstName("Ben")
                .lastName("Solo")
                .pesel("19023008655")
                .email("Bensol@gmail.com")
                .phoneNumber("+48 947 007 420")
                .build();
    }

    private LocalDateTime createLocalDateTimeExample() {
        return LocalDateTime.of(2020, Month.JUNE, 15, 13, 39);
    }

    private LocalDateTime createWrongLocalDateTimeExample() {
        return LocalDateTime.of(2020, Month.MAY, 15, 23, 39);
    }

    private BookingForm createBookingFormForTest() {
        return BookingForm.builder()
                .client(createClientForTest())
                .plannedFlightDateTime(createLocalDateTimeExample())
                .build();
    }

    @BeforeEach
    void setup() {
        bookingFormRepository.deleteAll();
    }

    @Test
    void deleteExistingBookingForm_successTest() {
        //given
        LocalDateTime dateTime = createLocalDateTimeExample();
        Client bob = createClientForTest();
        BookingForm bookingform = createBookingFormForTest();
        List<BookingForm> stubList = new ArrayList<>();
        stubList.add(bookingform);

        bookingFormRepository.save(bookingform);
        when(clientMapper.mapClientDto(any(ClientDto.class))).thenReturn(bob);
        when(bookingFormRepository.findByClientAndPlannedFlightDateTime(bob, dateTime)).thenReturn(stubList);
        //when
        bookingServiceDelete.deleteExistingBookingForm(createClientDtoForTest(), dateTime);
        //then
        verify(bookingFormRepository, times(1)).deleteBookingFormByClientAndPlannedFlightDateTime(bob, dateTime);
    }

    @Test
    void deleteExistingBookingForm_bookingFormDoesntExist_throwsExceptionTest() {
        //given
        //when
        Throwable throwable = catchThrowable(() -> bookingServiceDelete.deleteExistingBookingForm(null, null));
        //then
        assertThat(throwable).isInstanceOf(BadRequestException.class);
    }

    @Test
    void deleteExistingBookingForm_clientDataDoesntMatch_throwsExceptionTest() {
        //given
        LocalDateTime dateTime = createLocalDateTimeExample();
        Client ben = createWrongClientForTest();
        BookingForm bookingform = createBookingFormForTest();
        List<BookingForm> stubList = new ArrayList<>();

        bookingFormRepository.save(bookingform);
        when(clientMapper.mapClientDto(any(ClientDto.class))).thenReturn(ben);
        when(bookingFormRepository.findByClientAndPlannedFlightDateTime(ben, dateTime)).thenReturn(stubList);
        //when
        Throwable throwable = catchThrowable(() -> bookingServiceDelete.deleteExistingBookingForm(createWrongClientDtoForTest(), dateTime));
        //then
        assertThat(throwable).isInstanceOf(BadRequestException.class);
        verify(bookingFormRepository, times(0)).delete(any(BookingForm.class));
    }

    @Test
    void deleteExistingBookingForm_dateTimeDoesntMatch_throwsExceptionTest() {
        //given
        LocalDateTime wrongDateTime = createWrongLocalDateTimeExample();
        Client bob = createClientForTest();
        BookingForm bookingform = createBookingFormForTest();
        List<BookingForm> stubList = new ArrayList<>();

        bookingFormRepository.save(bookingform);
        when(clientMapper.mapClientDto(any(ClientDto.class))).thenReturn(bob);
        when(bookingFormRepository.findByClientAndPlannedFlightDateTime(bob, wrongDateTime)).thenReturn(stubList);
        //when
        Throwable throwable = catchThrowable(() -> bookingServiceDelete.deleteExistingBookingForm(createClientDtoForTest(), wrongDateTime));
        //then
        assertThat(throwable).isInstanceOf(BadRequestException.class);
        verify(bookingFormRepository, times(0)).delete(any(BookingForm.class));
    }
}
