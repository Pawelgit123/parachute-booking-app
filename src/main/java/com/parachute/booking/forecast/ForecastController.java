package com.parachute.booking.forecast;

import com.parachute.booking.forecast.api.ForecastClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forecast")
public class ForecastController {

    private final DayCreator dayCreator;
    private final ForecastClient forecastClient;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getForecasts(){

    }

    @GetMapping("/{year}-{month}-{day}")
    @ResponseStatus(HttpStatus.OK)
    public Forecasts getListOfForecastsForDay(@PathVariable int year,@PathVariable int month,@PathVariable int day){

        return new Forecasts(forecastClient.getForecast(dayCreator.createCalendarDayFromNumbers(year, month, day)));
    }


}
