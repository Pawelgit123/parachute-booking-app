package com.parachute.booking.plane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PlaneMapperTest {

    @Mock
    private PlaneRepository planeRepository;

    private PlaneDto createNewPlaneDtoForTest() {
        return PlaneDto.builder()
                .planeNumber(16L)
                .planeModel("F16")
                .id(1L)
                .build();
    }

    private Plane createNewPlaneForTest() {
        return Plane.builder()
                .planeNumber(22L)
                .planeModel("F22")
                .id(1L)
                .build();
    }

    @BeforeEach
    void setup() {
        planeRepository.deleteAll();

    }

    @Test
    void mapPlaneDto() {
        //given
        PlaneMapper planeMapper = new PlaneMapper();

        //when
        PlaneDto planeDto = planeMapper.mapPlaneDto(createNewPlaneForTest());

        //then
        assertThat(planeDto).isExactlyInstanceOf(PlaneDto.class);
        assertThat(planeDto.getId()).isEqualTo(1L);
        assertThat(planeDto.getPlaneModel()).isEqualTo("F22");
        assertThat(planeDto.getPlaneNumber()).isEqualTo(22L);
    }

    @Test
    void mapPlane() {
        //given
        PlaneMapper planeMapper = new PlaneMapper();

        //when
        Plane planeDto = planeMapper.mapPlane(createNewPlaneDtoForTest());

        //then
        assertThat(planeDto).isExactlyInstanceOf(Plane.class);
        assertThat(planeDto.getId()).isEqualTo(1L);
        assertThat(planeDto.getPlaneModel()).isEqualTo("F16");
        assertThat(planeDto.getPlaneNumber()).isEqualTo(16L);
    }
}
