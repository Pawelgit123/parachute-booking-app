package com.parachute.booking.flight;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findAllByPilotLicenseNumber(Long pilotLicenseNumber);

    Optional<Flight> findAllByPlaneNumber(Long planeNumber);

    Optional<Flight> findAllByLocalDateTime(LocalDateTime localDateTime);
}
