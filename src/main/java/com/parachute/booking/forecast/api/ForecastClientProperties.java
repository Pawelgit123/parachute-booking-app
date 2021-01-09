package com.parachute.booking.forecast.api;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Data
@ConfigurationProperties("openweather")
@Validated
public class ForecastClientProperties {

    @NotNull
    private String apiKey;
}
