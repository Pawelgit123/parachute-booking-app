package com.parachute.booking.forecast.api;

import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
//@EnableConfigurationProperties(ForecastClientProperties.class)
@Data
@ConfigurationProperties("openweather")
@Validated
public class ForecastClientProperties {

    @NotNull
    private String apiKey;
}
