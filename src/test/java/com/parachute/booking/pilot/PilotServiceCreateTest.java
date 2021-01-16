package com.parachute.booking.pilot;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;

class PilotServiceCreateTest {

  @Mock
  private PilotRepository pilotRepository;

  @Mock
  private PilotMapper pilotMapper;

  @InjectMocks
  private PilotServiceCreate pilotServiceCreate;
}
