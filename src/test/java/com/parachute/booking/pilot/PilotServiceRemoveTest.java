package com.parachute.booking.pilot;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parachute.booking.exceptions.NotFoundException;

@ExtendWith(MockitoExtension.class)
class PilotServiceRemoveTest {

    private static final Long ID = 1L;

    @Mock
    private PilotRepository pilotRepository;

    @InjectMocks
    private PilotServiceRemove pilotServiceRemove;

    private Pilot createPilotForTest() {
        return Pilot.builder()
                .pilotLicenseNumber(333L)
                .firstName("Jarosław")
                .surName("Adamski")
                .id(ID)
                .build();
    }

    @Test
    void shouldRemovePilotWhenPilotExists() {
        //given
        when(pilotRepository.findById(ID)).thenReturn(Optional.of(createPilotForTest()));

        //when
        pilotServiceRemove.removePilotById(ID);

        //then
        verify(pilotRepository).deleteById(ID);
    }

    @Test
    void shouldThrowWhenDeletingNonExistingPilot() {
        when(pilotRepository.findById(ID)).thenReturn(Optional.empty());

        assertThatExceptionOfType(NotFoundException.class)
            .isThrownBy(() -> pilotServiceRemove.removePilotById(ID));
        verify(pilotRepository, never()).deleteById(ID);
    }
}
