package com.parachute.booking.plane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlaneServiceRemoveTest {

    @Mock
    private PlaneRepository planeRepository;
    @Mock
    private PlaneServiceRemove planeServiceRemove;

    private Plane createPlaneForTest(){
        return Plane.builder()
                .planeNumber(17L)
                .planeModel("F17")
                .id(1L)
                .build();
    }

    @BeforeEach
    void setup(){
        planeRepository.deleteAll();
    }

    @Test
    void removePlaneById() {
        //given

        //when
        planeServiceRemove.removePlaneById(createPlaneForTest().getId());

        //then
        verify(planeServiceRemove, times(1)).removePlaneById(createPlaneForTest().getId());
    }

}
