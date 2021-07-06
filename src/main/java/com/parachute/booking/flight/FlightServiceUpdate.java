package com.parachute.booking.flight;

import com.parachute.booking.exceptions.InternalServerException;
import com.parachute.booking.exceptions.NotFoundException;
import com.parachute.booking.pilot.Pilot;
import com.parachute.booking.pilot.PilotRepository;
import com.parachute.booking.plane.Plane;
import com.parachute.booking.plane.PlaneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightServiceUpdate {

    private final FlightRepository flightRepository;
    private final PlaneRepository planeRepository;
    private final PilotRepository pilotRepository;

    //TODO block update for finished flights?

    public void patchFlightById(FlightDto flightDto, Long id) {

        if (flightDto == null) {
            throw new InternalServerException("No data to update Flight");
        }

        Optional<Flight> byId = flightRepository.findById(id);
        if (byId.isPresent()) {
            if (flightDto.getPlaneNumber() != null) {
                Flight flightToPatch = byId.get();
                Long planeNumber = flightDto.getPlaneNumber();
                Optional<Plane> byPlaneNumber = planeRepository.findByPlaneNumber(planeNumber);
                flightToPatch.setPlaneNumber(byPlaneNumber.get());
                flightRepository.save(flightToPatch);
                log.info("Plane for flight with ID: " + id + " has been updated");
            }
            if (flightDto.getPilotLicenseNumber() != null) {
                Flight flightToPatch = byId.get();
                Long pilotLicenseNumber = flightDto.getPilotLicenseNumber();
                Optional<Pilot> byPilotLicenseNumber = pilotRepository.findByPilotLicenseNumber(pilotLicenseNumber);
                flightToPatch.setPilotLicenseNumber(byPilotLicenseNumber.get());
                flightRepository.save(flightToPatch);
                log.info("Pilot for flight with ID: " + id + " has been updated");
            }
            if (flightDto.getLocalDateTime() != null) {
                Flight flightToPatch = byId.get();
                flightToPatch.setLocalDateTime(flightDto.getLocalDateTime());
                flightRepository.save(flightToPatch);
                log.info("Date for flight with ID: " + id + " has been updated");
            }

        } else {
            throw new NotFoundException("Not found flight to update with ID: " + id);
        }
    }

    public void updateFlightById(FlightDto flightDto, Long id) {

        if (flightDto == null) {
            throw new InternalServerException("No data to update Flight");
        }

        Long planeNumber = flightDto.getPlaneNumber();
        Long pilotLicenseNumber = flightDto.getPilotLicenseNumber();

        Optional<Flight> byId = flightRepository.findById(id);
        Optional<Plane> byPlaneNumber = planeRepository.findByPlaneNumber(planeNumber);
        Optional<Pilot> byPilotLicenseNumber = pilotRepository.findByPilotLicenseNumber(pilotLicenseNumber);

        if (byId.isPresent() && byPlaneNumber.isPresent() && byPilotLicenseNumber.isPresent()) {
            Flight flightToUpdate = byId.get();

            flightToUpdate.setLocalDateTime(flightDto.getLocalDateTime());
            flightToUpdate.setPlaneNumber(byPlaneNumber.get());
            flightToUpdate.setPilotLicenseNumber(byPilotLicenseNumber.get());

            flightRepository.save(flightToUpdate);
            log.info("Flight with ID: " + id + " has been updated");

        } else {
            throw new NotFoundException("Not found flight to update with ID: " + id);
        }
    }

}
