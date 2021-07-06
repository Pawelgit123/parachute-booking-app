package com.parachute.booking.forecast;

import com.parachute.booking.forecast.api.ForecastResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForecastMapper {
    private void setRainIfPresent(ForecastResponse.SingleForecast singleForecast, Forecast forecast) {
        if (singleForecast.getRain() != null) {
            forecast.setRainPrecipitation(singleForecast.getRain().getPrecipitationHeight());
        }
    }
    private void setRainIfPresent(ForecastResponse.SingleForecast singleForecast, ForecastDto forecastDto) {
        if (singleForecast.getRain() != null) {
            forecastDto.setRainPrecipitation(singleForecast.getRain().getPrecipitationHeight());
        }
    }
    private void setSnowIfPresent(ForecastResponse.SingleForecast singleForecast, Forecast forecast) {
        if (singleForecast.getSnow() != null) {
            forecast.setSnowPrecipitation(singleForecast.getSnow().getPrecipitationHeight());
        }
    }
    private void setSnowIfPresent(ForecastResponse.SingleForecast singleForecast, ForecastDto forecastDto) {
        if (singleForecast.getSnow() != null) {
            forecastDto.setSnowPrecipitation(singleForecast.getSnow().getPrecipitationHeight());
        }
    }

    public Forecast mapToForecast(ForecastResponse.SingleForecast singleForecast) {
        Forecast forecast = new Forecast();
        forecast.setTemp(singleForecast.getGeneral().getTemp());
        forecast.setTempFeelsLike(singleForecast.getGeneral().getFeelsLike());
        forecast.setPressureAtSeaLevelhPa(singleForecast.getGeneral().getSeaLevel());
        forecast.setPressureAtGroundLevelhPa(singleForecast.getGeneral().getGrndLevel());
        forecast.setRelativeHumidity(singleForecast.getGeneral().getHumidity());
        forecast.setWeatherDescription(singleForecast.getWeather().stream().findFirst().orElseThrow().getDescription());
        forecast.setCloudiness(singleForecast.getClouds().getAll());
        forecast.setWindSpeed(singleForecast.getWind().getSpeed());
        forecast.setWindDegree(singleForecast.getWind().getDeg());
        setRainIfPresent(singleForecast, forecast);
        setSnowIfPresent(singleForecast, forecast);
        forecast.setVisibility(singleForecast.getVisibility());
        forecast.setProbabilityOfPrecipitation(singleForecast.getPop());
        forecast.setDateAndTime(singleForecast.getDateAndTime());
        return forecast;
    }

    public Forecast mapToForecast(ForecastDto forecastDto) {
        Forecast forecast = new Forecast();
        forecast.setTemp(forecastDto.getTemp());
        forecast.setTempFeelsLike(forecastDto.getTempFeelsLike());
        forecast.setPressureAtSeaLevelhPa(forecastDto.getPressureAtSeaLevelhPa());
        forecast.setPressureAtGroundLevelhPa(forecastDto.getPressureAtGroundLevelhPa());
        forecast.setRelativeHumidity(forecastDto.getRelativeHumidity());
        forecast.setWeatherDescription(forecastDto.getWeatherDescription());
        forecast.setCloudiness(forecastDto.getCloudiness());
        forecast.setWindSpeed(forecastDto.getWindSpeed());
        forecast.setWindDegree(forecastDto.getWindDegree());
        if (forecastDto.getRainPrecipitation() != null) {
            forecast.setRainPrecipitation(forecastDto.getRainPrecipitation());
        }
        if (forecastDto.getSnowPrecipitation() != null) {
            forecast.setSnowPrecipitation(forecastDto.getSnowPrecipitation());
        }
        forecast.setVisibility(forecastDto.getVisibility());
        forecast.setProbabilityOfPrecipitation(forecastDto.getProbabilityOfPrecipitation());
        forecast.setDateAndTime(forecastDto.getDateAndTime());
        return forecast;
    }

    public ForecastDto mapToForecastDto(ForecastResponse.SingleForecast singleForecast) {
        ForecastDto forecastDto = new ForecastDto();
        forecastDto.setTemp(singleForecast.getGeneral().getTemp());
        forecastDto.setTempFeelsLike(singleForecast.getGeneral().getFeelsLike());
        forecastDto.setPressureAtSeaLevelhPa(singleForecast.getGeneral().getSeaLevel());
        forecastDto.setPressureAtGroundLevelhPa(singleForecast.getGeneral().getGrndLevel());
        forecastDto.setRelativeHumidity(singleForecast.getGeneral().getHumidity());
        forecastDto.setWeatherDescription(singleForecast.getWeather().stream().findFirst().orElseThrow().getDescription());
        forecastDto.setCloudiness(singleForecast.getClouds().getAll());
        forecastDto.setWindSpeed(singleForecast.getWind().getSpeed());
        forecastDto.setWindDegree(singleForecast.getWind().getDeg());
        setRainIfPresent(singleForecast, forecastDto);
        setSnowIfPresent(singleForecast, forecastDto);
        forecastDto.setVisibility(singleForecast.getVisibility());
        forecastDto.setProbabilityOfPrecipitation(singleForecast.getPop());
        forecastDto.setDateAndTime(singleForecast.getDateAndTime());
        return forecastDto;
    }
}
