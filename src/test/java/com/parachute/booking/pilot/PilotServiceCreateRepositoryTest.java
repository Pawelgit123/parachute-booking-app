package com.parachute.booking.pilot;

import com.parachute.booking.exceptions.InternalServerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PilotServiceCreateRepositoryTest {

    @Mock
    private PilotRepository pilotRepository;
    @InjectMocks
    private PilotServiceCreate pilotServiceCreate;
    @Mock
    private PilotMapper pilotMapper;

    @BeforeEach
    void setup() {
        pilotRepository.deleteAll();
    }

    @Test
    void createPilot_savePilotToRepository() {
        //given
        when(pilotRepository.save(any(Pilot.class))).thenReturn(new Pilot());
        when(pilotMapper.mapPilot(new PilotDto())).thenReturn(new Pilot());
        when(pilotMapper.mapPilotDto(new Pilot())).thenReturn(new PilotDto());

        //when
        PilotDto pilotdto = pilotServiceCreate.createNewPilot(new PilotDto());

        //then
        assertThat(pilotdto).isExactlyInstanceOf(PilotDto.class);
        verify(pilotRepository).save(any(Pilot.class));
    }

    @Test
    void createNewPilot_InternalServerError_byNull() {
        //when
        Throwable throwable = catchThrowable(() -> pilotServiceCreate.createNewPilot(null));

        //then
        assertThat(throwable).isInstanceOf(InternalServerException.class);
        verify(pilotRepository, times(0)).save(any(Pilot.class));
    }

}
