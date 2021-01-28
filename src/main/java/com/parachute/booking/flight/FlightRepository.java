package com.parachute.booking.flight;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByPilotLicenseNumber(Long pilotLicenseNumber);

    Optional<Flight> findByPlaneNumber(Long planeNumber);

    Optional<Flight> findByLocalDateTime(LocalDateTime localDateTime);
}
