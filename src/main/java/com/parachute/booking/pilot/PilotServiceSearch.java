package com.parachute.booking.pilot;

import com.parachute.booking.exceptions.InternalServerException;
import com.parachute.booking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PilotServiceSearch {

    private final PilotRepository pilotRepository;
    private final PilotMapper pilotMapper;

    public Set<PilotDto> getAllPilots() {

        List<Pilot> all = pilotRepository.findAll();
        new HashSet<>(all);

        return all.stream()
                .map(pilotMapper::mapPilotDto)
                .collect(Collectors.toSet());
    }

    public PilotDto findPilotById(Long id) {
        Optional<Pilot> byId = pilotRepository.findById(id);
        return pilotMapper.mapPilotDto(byId
                .orElseThrow(() -> new NotFoundException("Not found pilot with ID: " + id)));
    }

    public PilotDto findPilotByLicenseNumber(Long licenseNumber) {
        Optional<Pilot> byId = pilotRepository.findByPilotLicenseNumber(licenseNumber);
        return pilotMapper.mapPilotDto(byId
                .orElseThrow(() -> new NotFoundException("Not found pilot with License: " + licenseNumber)));
    }

    public PilotDto findPilotByFirstName(String name) {
        Optional<Pilot> byId = pilotRepository.findByFirstName(name);
        return pilotMapper.mapPilotDto(byId
                .orElseThrow(() -> new NotFoundException("Not found pilot with name: " + name)));
    }

    public PilotDto findPilotBySurName(String surName) {
        Optional<Pilot> byId = pilotRepository.findBySurName(surName);
        return pilotMapper.mapPilotDto(byId
                .orElseThrow(() -> new NotFoundException("Not found pilot with surname: " + surName)));
    }

}
