package com.parachute.booking.booking;

import com.parachute.booking.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingFormRepository extends JpaRepository <BookingForm, Long> {
    @Query("SELECT b FROM bookings b left join b.client c WHERE c.firstName = :firstname ")
    List<BookingForm> findByClientAndPlannedFlightDateTime(Client client, LocalDateTime localDateTime);
//TODO here and above   @Query("SELECT ")
    long deleteBookingFormByClientAndPlannedFlightDateTime(Client client, LocalDateTime localDateTime);
}
