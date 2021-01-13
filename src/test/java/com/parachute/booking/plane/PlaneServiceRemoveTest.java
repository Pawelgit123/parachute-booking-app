package com.parachute.booking.plane;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlaneServiceRemoveTest {

    @Mock
    PlaneRepository planeRepository;
    @Mock
    PlaneServiceRemove planeServiceRemove;

    private Plane createPlaneForTest(){
        return Plane.builder()
                .planeNumber(17L)
                .planeModel("F17")
                .id(1L)
                .build();
    }

    @Test
    void removePlaneById() {
        //given
       planeRepository.save(createPlaneForTest());

        //when
        planeServiceRemove.removePlaneById(createPlaneForTest().getId());

        //then
        verify(planeServiceRemove, times(1)).removePlaneById(createPlaneForTest().getId());
    }

}
