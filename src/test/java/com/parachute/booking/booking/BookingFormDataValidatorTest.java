package com.parachute.booking.booking;

import com.parachute.booking.client.ClientDto;
import com.parachute.booking.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
class BookingFormDataValidatorTest {

    BookingFormDataValidator bookingFormDataValidator = new BookingFormDataValidator();

    private ClientDto createBadClientDtoForTest() {
        return ClientDto.builder()
                .firstName("")
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
    void validateBookingFormData_missingOneFieldOfClientDtoTest() {
        //when
        Throwable throwable = catchThrowable(() ->
                bookingFormDataValidator.validateBookingFormData(createBadClientDtoForTest(), createLocalDateTimeExample()));
        //then
        assertThat(throwable).isInstanceOf(BadRequestException.class);
    }

    @Test
    void validateBookingFormData_missingLocalDateTimeTest() {
        //when
        Throwable throwable = catchThrowable(() ->
                bookingFormDataValidator.validateBookingFormData(createBadClientDtoForTest(), null));
        //then
        assertThat(throwable).isInstanceOf(BadRequestException.class);
    }
}
