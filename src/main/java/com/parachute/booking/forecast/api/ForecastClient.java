package com.parachute.booking.forecast.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parachute.booking.forecast.Forecast;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ForecastClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ForecastClientProperties forecastClientProperties;

    public Optional<Forecast> getForecast(LocalDateTime localDateTime) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("id", 3088034)
                .queryParam("appId", forecastClientProperties.getApiKey())
                .queryParam("lang", "pl")
                .queryParam("units", "metric")
                .build()
                .toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String body = response.getBody();

        if (response.getStatusCode().isError()) {
            log.error("Connection error with url: " + url + ", status code: " + response.getStatusCode().value());
            return Optional.empty()
        }

        try {
            ForecastResponse forecastResponse = objectMapper.readValue(body, ForecastResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
