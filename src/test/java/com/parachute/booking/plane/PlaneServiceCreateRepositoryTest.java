package com.parachute.booking.plane;

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
class PlaneServiceCreateRepositoryTest {

    @Mock
    private PlaneRepository planeRepository;
    @InjectMocks
    private PlaneServiceCreate planeServiceCreate;
    @Mock
    private PlaneMapper planeMapper;

    @BeforeEach
    void setup() {
        planeRepository.deleteAll();
    }

    @Test
    void createPlane_savePlaneToRepository() {
        //given
        when(planeRepository.save(any(Plane.class))).thenReturn(new Plane());
        when(planeMapper.mapPlane(new PlaneDto())).thenReturn(new Plane());
        when(planeMapper.mapPlaneDto(new Plane())).thenReturn(new PlaneDto());

        //when
        PlaneDto planeDto = planeServiceCreate.createNewPlane(new PlaneDto());

        //then
        assertThat(planeDto).isExactlyInstanceOf(PlaneDto.class);
        verify(planeRepository).save(any(Plane.class));
    }

    @Test
    void createNewPlane_InternalServerError_byNull() {
        //when
        Throwable throwable = catchThrowable(() -> planeServiceCreate.createNewPlane(null));

        //then
        assertThat(throwable).isInstanceOf(InternalServerException.class);
        verify(planeRepository, times(0)).save(any(Plane.class));
    }

}
