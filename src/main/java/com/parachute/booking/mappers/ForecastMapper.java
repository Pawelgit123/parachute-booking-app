package com.parachute.booking.mappers;

import com.parachute.booking.forecast.Forecast;
import com.parachute.booking.forecast.ForecastDto;
import com.parachute.booking.forecast.api.ForecastResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForecastMapper {

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
    public Forecast mapToForecast(ForecastDto forecastDto){
        return new Forecast();//todo finish this mapper
    }
}
