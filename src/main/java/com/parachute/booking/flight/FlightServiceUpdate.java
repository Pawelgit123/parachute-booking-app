package com.parachute.booking.flight;

import com.parachute.booking.exceptions.InternalServerException;
import com.parachute.booking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightServiceUpdate {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    //TODO block update for finished flights?

    public void patchFlightById(FlightDto flightDto, Long id) {

        if (flightDto == null) {
            throw new InternalServerException("No data to update Flight");
        }
        Optional<Flight> byId = flightRepository.findById(id);

        if (byId.isPresent()) {
            Flight flightToUpdate = byId.get();
            Flight flightNewInfo = flightMapper.mapFlight(flightDto);

            Flight flight = new Flight();
//            if (flightDto.getPlaneNumber() != null) {
//                flight.setPlaneNumber(flightDto.getPlaneNumber());
//            }
//            if (flightDto.getPilotLicenseNumber() != null) {
//                flight.setPilotLicenseNumber(flightDto.getPilotLicenseNumber());
//            }
            if (flightDto.getLocalDateTime() != null) {
                flight.setLocalDateTime(flightDto.getLocalDateTime());
            }
            flight.setId(id);

            flightRepository.save(flight);

        } else {
            throw new NotFoundException("Not found flight to update with ID: " + id);
        }
    }

    public void updateFlightById(FlightDto flightDto, Long id) {

        if (flightDto == null) {
            throw new InternalServerException("No data to update Flight");
        }
        Optional<Flight> byId = flightRepository.findById(id);

        if (byId.isPresent()) {
            Flight flightToUpdate = byId.get();
            Flight flightNewInfo = flightMapper.mapFlight(flightDto);
            flightToUpdate.setPilotLicenseNumber(flightNewInfo.getPilotLicenseNumber());
            flightToUpdate.setPlaneNumber(flightNewInfo.getPlaneNumber());
            flightToUpdate.setLocalDateTime(flightNewInfo.getLocalDateTime());
            flightRepository.save(flightToUpdate);
            log.info("Flight " + id + "has been updated");

        } else {
            throw new NotFoundException("Not found flight to update with ID: " + id);
        }
    }

}
