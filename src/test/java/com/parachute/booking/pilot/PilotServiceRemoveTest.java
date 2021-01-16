package com.parachute.booking.pilot;

import com.parachute.booking.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PilotServiceRemoveTest {

    private static final Long ID = 1L;

    @Mock
    private PilotRepository pilotRepository;
    @InjectMocks
    private PilotServiceRemove pilotServiceRemove;

    private Pilot createPilotForTest(){
        return Pilot.builder()
                .pilotLicenseNumber(333L)
                .firstName("Jarosław")
                .surName("Adamski")
                .id(ID)
                .build();
    }

    @BeforeEach
    void setup(){
        pilotRepository.deleteAll();
    }

    @Test
    void removePilotById_whenPilotExists() {
        //given
        when(pilotRepository.findById(ID)).thenReturn(Optional.of(createPilotForTest()));

        //when
        pilotServiceRemove.removePilotById(ID);

        //then
        verify(pilotRepository, times(1)).deleteById(ID);
    }

    @Test
    void removePilotById_whenPilotDoesntExist() {
        //given
        when(pilotRepository.findById(ID)).thenReturn(Optional.empty());

        //when

        //then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> pilotServiceRemove.removePilotById(ID));
        verify(pilotRepository, never()).deleteById(ID);
    }

}
