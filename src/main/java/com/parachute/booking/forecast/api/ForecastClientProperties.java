package com.parachute.booking.forecast.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("openweather")
public class ForecastClientProperties {
    private String apiKey;
}
