package com.parachute.booking.forecast.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parachute.booking.forecast.Forecast;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ForecastClient {

    private static final String HTTP = "http";
    private static final String HOST = "api.openweathermap.org/data/2.5/forecast";
    private static final String ID_PARAM = "id";
    private static final int CITY_ID = 3088034;
    private static final String APPID_PARAM = "appId";
    private static final String LANG_PARAM = "lang";
    private static final String LANG_PL = "pl";
    private static final String UNITS_PARAM = "units";
    private static final String UNITS_METRIC = "metric";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ForecastClientProperties forecastClientProperties;

    public List<Forecast> getForecast(String formattedYearMonthDay) {
        String url = UriComponentsBuilder.newInstance()
                .scheme(HTTP)
                .host(HOST)
                .queryParam(ID_PARAM, CITY_ID)
                .queryParam(APPID_PARAM, forecastClientProperties.getApiKey())
                .queryParam(LANG_PARAM, LANG_PL)
                .queryParam(UNITS_PARAM, UNITS_METRIC)
                .build()
                .toUriString();

        try {
            ResponseEntity<ForecastResponse> response = restTemplate.getForEntity(url, ForecastResponse.class);
            ForecastResponse body = response.getBody();

            return body.getSingleForecastList()
                    .stream()
                    .filter(f -> f.getDateAndTime().matches(formattedYearMonthDay))
                    .map(this::mapToForecast)
                    .collect(Collectors.toList());

        } catch (HttpStatusCodeException e) {
            log.error("Forecast data could not be retrieved.", e);
            return Collections.emptyList();
        } catch (RestClientException e) {
            log.error("Connection error with the host", e);
            return Collections.emptyList();
        }

    }

    public Forecast mapToForecast(ForecastResponse.SingleForecast singleForecast) {
        Forecast forecast = new Forecast();
        forecast.setTemp(singleForecast.getGeneral().getTemp());
        forecast.setTempFeelsLike(singleForecast.getGeneral().getFeelsLike());
        forecast.setPressureAtSeaLevelhPa(singleForecast.getGeneral().getSeaLevel());
        forecast.setPressureAtGroundLevelhPa(singleForecast.getGeneral().getGrndLevel());
        forecast.setRelativeHumidity(singleForecast.getGeneral().getHumidity());
        forecast.setWeatherDescription(singleForecast.getWeather().getDescription());
        forecast.setCloudiness(singleForecast.getClouds().getAll());
        forecast.setWindSpeed(singleForecast.getWind().getSpeed());
        forecast.setWindDegree(singleForecast.getWind().getDeg());
        if (singleForecast.getRain() != null) {
            forecast.setRainPrecipitation(singleForecast.getRain().getPrecipitationHeight());
        }
        if (singleForecast.getSnow() != null) {
            forecast.setSnowPrecipitation(singleForecast.getSnow().getPrecipitationHeight());
        }
        forecast.setVisibility(singleForecast.getVisibility());
        forecast.setProbabilityOfPrecipitation(singleForecast.getPop());
        forecast.setDateAndTime(singleForecast.getDateAndTime());
        return forecast;
    }
}
