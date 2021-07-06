package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;
import com.parachute.booking.client.ClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BookingFormMapperTest {

    @Mock
    private BookingFormRepository bookingFormRepository;

    private final LocalDateTime localDateTime = LocalDateTime.now();

    @Spy
    private ClientMapper clientMapper;

    @Mock
    private ClientDto clientDto;

    @Mock
    private Client client;

    private BookingForm createBookingFormForTest() {
        return BookingForm.builder()
                .plannedFlightDateTime(localDateTime)
                .client(clientMapper.mapClientDto(clientDto))
                .build();
    }

    private BookingFormDto createBookingFormDtoForTest() {
        return BookingFormDto.builder()
                .plannedFlightDateTime(localDateTime)
                .clientDto(clientMapper.mapClient(client))
                .build();
    }

    @BeforeEach
    void setBookingFormRepositoryClean() {
        bookingFormRepository.deleteAll();
    }

    @Test
    void mapBookingFormToBookingFormDto() {
        //given
        BookingFormMapper bookingFormMapper = new BookingFormMapper(clientMapper);

        //when
        BookingFormDto bookingFormDto = bookingFormMapper.mapBookingForm(createBookingFormForTest());

        //then
        assertThat(bookingFormDto).isExactlyInstanceOf(BookingFormDto.class);
        assertThat(bookingFormDto.getPlannedFlightDateTime()).isEqualTo(localDateTime);
        assertThat(bookingFormDto.getClientDto()).isEqualTo(clientMapper.mapClient(client));
    }

    @Test
    void mapBookingFormDtoToBookingForm() {
        //given
        BookingFormMapper bookingFormMapper = new BookingFormMapper(clientMapper);

        //when
        BookingForm bookingForm = bookingFormMapper.mapBookingFormDto(createBookingFormDtoForTest());

        //then
        assertThat(bookingForm).isExactlyInstanceOf(BookingForm.class);
        assertThat(bookingForm.getPlannedFlightDateTime()).isEqualTo(localDateTime);
        assertThat(bookingForm.getClient()).isEqualTo(clientMapper.mapClientDto(clientDto));
    }
}
