package com.parachute.booking.forecast;

import com.parachute.booking.forecast.api.ForecastClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forecast")
public class ForecastController {

    private final ForecastClient forecastClient;
    private final String UNIVERSALSYMBOL = "-";
    private final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ForecastDto> getForecasts() {
        return forecastClient.getForecast(UNIVERSALSYMBOL);
    }

    @PostMapping("/daily")
    @ResponseStatus(HttpStatus.OK)
    public List<ForecastDto> getListOfForecastsForDay2(@RequestBody LocalDateTime localDateTime) {
        return forecastClient.getForecast(localDateTime.format(DATETIMEFORMATTER));
    }
}
