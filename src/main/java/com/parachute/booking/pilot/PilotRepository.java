package com.parachute.booking.pilot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PilotRepository extends JpaRepository<Pilot, Long> {

    Optional<Pilot> findByFirstName(String firstName);

    Optional<Pilot> findBySurName(String surName);

    Optional<Pilot> findByPilotLicenseNumber(Long pilotLicenseNumber);

}
