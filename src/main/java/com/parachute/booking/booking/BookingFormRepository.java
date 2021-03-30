package com.parachute.booking.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingFormRepository extends JpaRepository<BookingForm, Long> {
    @Query("SELECT b FROM bookings b left join b.client c WHERE c.firstName = :firstname AND c.lastName = :lastname " +
            "AND c.email = :email AND c.phoneNumber = :phonenumber AND c.pesel = :pesel " +
            "AND b.plannedFlightDateTime = :flightdate")
    List<BookingForm> findByClientAndPlannedFlightDateTime(String firstname, String lastname, String email
            , String phonenumber, String pesel, LocalDateTime flightdate);

    @Query("DELETE FROM bookings b WHERE b.client.firstName = :firstname AND b.client.lastName = :lastname " +
            "AND b.client.email = :email AND b.client.phoneNumber = :phonenumber AND b.client.pesel = :pesel " +
            "AND b.plannedFlightDateTime = :flightdate")
    long deleteBookingFormByClientAndPlannedFlightDateTime(String firstname, String lastname, String email
            , String phonenumber, String pesel, LocalDateTime flightdate);
}
