package com.parachute.booking.plane;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

    Optional<Plane> findByPlaneModel(String planeModel);

    Optional<Plane> findByPlaneNumber(Long planeNumber);

}
