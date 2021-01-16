package com.parachute.booking.pilot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
class PilotServiceCreatNullTest {

    @Mock
    PilotRepository pilotRepository;
    @InjectMocks
    PilotServiceCreate pilotServiceCreate;

    private PilotDto createNewPliotDtoWithNullsForTest() {
        return PilotDto.builder().build();

    }

    @BeforeEach
    void setup() {
        pilotRepository.deleteAll();
    }

    @Test
    void createNewPilot() {

        Throwable throwable = catchThrowable(() -> pilotServiceCreate.createNewPilot(createNewPliotDtoWithNullsForTest()));

        assertThat(throwable).isExactlyInstanceOf(NullPointerException.class);
    }

}
