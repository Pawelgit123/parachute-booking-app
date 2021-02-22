package com.parachute.booking.plane;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
class PlaneServiceCreateNullTest {

    @InjectMocks
    private PlaneServiceCreate planeServiceCreate;

    @Test
    void createNewPlane_whenIsNull() {
        PlaneDto planeDto = new PlaneDto(null, null, null, null);

        Throwable result = catchThrowable(() -> planeServiceCreate.createNewPlane(planeDto));

        assertThat(result).isExactlyInstanceOf(NullPointerException.class);
    }
}
