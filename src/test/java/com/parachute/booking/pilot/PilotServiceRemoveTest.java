package com.parachute.booking.pilot;

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

    @Test
    void removePilotById() {
        //given
        pilotRepository.save(createPilotForTest());

        //when
        pilotServiceRemove.removePilotById(createPilotForTest().getId());

        //then
        verify(pilotServiceRemove, times(1)).removePilotById(createPilotForTest().getId());
    }
}
