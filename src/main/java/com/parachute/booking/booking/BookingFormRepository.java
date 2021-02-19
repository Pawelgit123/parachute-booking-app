package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingFormRepository extends JpaRepository <BookingForm, Long> {
    List<BookingForm> findByClientAndPlannedFlightDateTime(Client client, LocalDateTime localDateTime);
    long deleteBookingFormByClientAndPlannedFlightDateTime(Client client, LocalDateTime localDateTime);
}
