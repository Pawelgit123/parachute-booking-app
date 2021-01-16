package com.parachute.booking.pilot;

import com.parachute.booking.flight.FlightDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PilotServiceCreateRepositoryTest {

    @Mock
    PilotRepository pilotRepository;
    @InjectMocks
    PilotServiceCreate pilotServiceCreate;
    @Mock
    PilotMapper pilotMapper;

    private PilotDto createNewPilotDtoForTest() {

        return PilotDto.builder()
                .pilotLicenseNumber(444L)
                .firstName("Mariusz")
                .surName("Janowski")
                .id(1L)
                .build();
    }

    @BeforeEach
    void setup() {
        pilotRepository.deleteAll();
    }

    @Test
    void createPilot_savePilotToRepository() {
        //given
        when(pilotRepository.save(any(Pilot.class))).thenReturn(new Pilot());
        when(pilotMapper.mapPilotDto(new Pilot())).thenReturn(new PilotDto());

        //when
        PilotDto pilotdto = pilotServiceCreate.createNewPilot(new PilotDto());

        //then
        assertThat(pilotdto).isExactlyInstanceOf(PilotDto.class);
        verify(pilotRepository).save(any(Pilot.class));
    }

}
