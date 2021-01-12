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
import java.util.Optional;

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

    public Optional<Forecast> getForecast(LocalDateTime localDateTime) {
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

        } catch (HttpStatusCodeException e) {
            log.error("Forecast data could not be retrieved.", e);
            return Optional.empty();
        } catch (RestClientException e) {
            log.error("Connection error with the host", e);
            return Optional.empty();
        }


        return Optional.empty();//todo finish this method
    }
}
