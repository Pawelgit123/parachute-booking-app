package com.parachute.booking.pilot;

import com.parachute.booking.exceptions.InternalServerException;
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
                .map(pilotMapper::mapPilotToDto)
                .collect(Collectors.toSet());
    }

    public PilotDto findPilotById(Long id) {
        Optional<Pilot> byId = pilotRepository.findById(id);
        return pilotMapper.mapPilotToDto(byId
                .orElseThrow(() -> new InternalServerException("No found pilot with ID: " + id)));
    }
}
