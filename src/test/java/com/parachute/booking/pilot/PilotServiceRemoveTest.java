package com.parachute.booking.pilot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PilotServiceRemoveTest {

    @Mock
    PilotRepository pilotRepository;
    @Mock
    PilotServiceRemove pilotServiceRemove;

    private Pilot createPilotForTest(){
        return Pilot.builder()
                .pilotLicenseNumber(333L)
                .firstName("Jarosław")
                .surName("Adamski")
                .id(1L)
                .build();
    }

    @BeforeEach
    void setup(){
        pilotRepository.deleteAll();
    }

    @Test
    void removePilotById() {
        //given

        //when
        pilotServiceRemove.removePilotById(createPilotForTest().getId());

        //then
        verify(pilotServiceRemove, times(1)).removePilotById(createPilotForTest().getId());
    }
}
