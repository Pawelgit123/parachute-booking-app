package com.parachute.booking.forecast;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forecasts {
  private List<ForecastDto> forecasts;
}
