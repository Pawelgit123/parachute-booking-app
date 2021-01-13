package com.parachute.booking.pilot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PilotMapperTest {

    @Mock
    PilotRepository pilotRepository;

    private PilotDto createPilotDtoForTest() {
        return PilotDto.builder()
                .pilotLicenseNumber(111L)
                .firstName("Adam")
                .surName("Kowalski")
                .id(1L)
                .build();
    }

    private Pilot createPilotForTest() {
        return Pilot.builder()
                .pilotLicenseNumber(222L)
                .firstName("Andrzej")
                .surName("Nowak")
                .id(1L)
                .build();
    }

    @BeforeEach
    void setup(){
        pilotRepository.deleteAll();
    }

    @Test
    void mapPilotDto() {
        //given
        PilotMapper pilotMapper = new PilotMapper();

        //when
        PilotDto pilotDto = pilotMapper.mapPilotDto(createPilotForTest());

        //then
        assertThat(pilotDto).isExactlyInstanceOf(PilotDto.class);
        assertThat(pilotDto.getId()).isEqualTo(1L);
        assertThat(pilotDto.getFirstName()).isEqualTo("Andrzej");
        assertThat(pilotDto.getSurName()).isEqualTo("Nowak");
        assertThat(pilotDto.getPilotLicenseNumber()).isEqualTo(222L);
    }

    @Test
    void mapPilot() {
        //given
        PilotMapper pilotMapper = new PilotMapper();

        //when
        Pilot pilot = pilotMapper.mapPilot(createPilotDtoForTest());

        //then
        assertThat(pilot).isExactlyInstanceOf(Pilot.class);
        assertThat(pilot.getId()).isEqualTo(1L);
        assertThat(pilot.getFirstName()).isEqualTo("Adam");
        assertThat(pilot.getSurName()).isEqualTo("Kowalski");
        assertThat(pilot.getPilotLicenseNumber()).isEqualTo(111L);
    }
}
