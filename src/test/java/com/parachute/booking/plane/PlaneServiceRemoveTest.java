package com.parachute.booking.plane;

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
class PlaneServiceRemoveTest {

    private static final Long ID = 1L;

    @Mock
    private PlaneRepository planeRepository;
    @InjectMocks
    private PlaneServiceRemove planeServiceRemove;

    private Plane createNewPlaneForTest() {
        return Plane.builder()
                .planeNumber(35L)
                .planeModel("F35")
                .id(ID)
                .build();
    }

    @BeforeEach
    void setup() {
        planeRepository.deleteAll();
    }

    @Test
    void removePilotById_whenPilotExists() {
        //given
        when(planeRepository.findById(ID)).thenReturn(Optional.of(createNewPlaneForTest()));

        //when
        planeServiceRemove.removePlaneById(ID);

        //then
        verify(planeRepository, times(1)).deleteById(ID);
    }

    @Test
    void removePilotById_whenPilotDoesntExist() {
        //given
        when(planeRepository.findById(ID)).thenReturn(Optional.empty());

        //when

        //then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> planeServiceRemove.removePlaneById(ID));
        verify(planeRepository, never()).deleteById(ID);
    }

}
