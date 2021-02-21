package com.parachute.booking.forecast;

import com.parachute.booking.client.ClientDto;
import com.parachute.booking.client.ClientMapper;
import com.parachute.booking.forecast.api.ForecastResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ForecastMapperTest {

    @Mock
    private ForecastRepository forecastRepository;

    private Forecast createForecastForTest() {
        return Forecast.builder()
                .temp(5.5F)
                .tempFeelsLike(0.74F)
                .pressureAtSeaLevelhPa(1020)
                .pressureAtGroundLevelhPa(1020)
                .relativeHumidity(77)
                .weatherDescription("rozproszone chmury")
                .cloudiness(44)
                .windSpeed(4.35F)
                .windDegree(195)
                .rainPrecipitation(null)
                .snowPrecipitation(null)
                .visibility(370)
                .probabilityOfPrecipitation(0F)
                .dateAndTime("2021-02-21 12:00:00")
                .build();
    }

    private ForecastResponse.SingleForecast createSingleForecast() {
        return ForecastResponse.SingleForecast.builder()
                .general(ForecastResponse.SingleForecast.General.builder()
                        .temp(5.5F)
                        .feelsLike(0.74F)
                        .seaLevel(1020)
                        .grndLevel(1020)
                        .humidity(77)
                        .build())
                .weather(List.of(ForecastResponse.SingleForecast.Weather.builder().description("rozproszone chmury").build()))
                .clouds(ForecastResponse.SingleForecast.Clouds.builder().all(44).build())
                .wind(ForecastResponse.SingleForecast.Wind.builder().speed(4.35F).deg(195).build())
                .rain(null)
                .snow(null)
                .visibility(370)
                .pop(0F)
                .dateAndTime("2021-02-21 12:00:00")
                .build();
    }

    private ForecastDto createForecastDtoForTest() {
        return ForecastDto.builder()
                .temp(5.5F)
                .tempFeelsLike(0.74F)
                .pressureAtSeaLevelhPa(1020)
                .pressureAtGroundLevelhPa(1020)
                .relativeHumidity(77)
                .weatherDescription("rozproszone chmury")
                .cloudiness(44)
                .windSpeed(4.35F)
                .windDegree(195)
                .rainPrecipitation(null)
                .snowPrecipitation(null)
                .visibility(370)
                .probabilityOfPrecipitation(0F)
                .dateAndTime("2021-02-21 12:00:00")
                .build();
    }

    @BeforeEach
    void setForecastRepositoryClean() {
        forecastRepository.deleteAll();
    }

    @Test
    void mapToForecastFromSingleForecast() {
        //given
        ForecastMapper forecastMapper = new ForecastMapper();

        //when
        Forecast forecast = forecastMapper.mapToForecast(createSingleForecast());

        //then
        assertThat(forecast).isExactlyInstanceOf(Forecast.class);
        assertThat(forecast.getTemp()).isEqualTo(5.5F);
        assertThat(forecast.getTempFeelsLike()).isEqualTo(0.74F);
        assertThat(forecast.getPressureAtSeaLevelhPa()).isEqualTo(1020);
        assertThat(forecast.getPressureAtGroundLevelhPa()).isEqualTo(1020);
        assertThat(forecast.getRelativeHumidity()).isEqualTo(77);
        assertThat(forecast.getWeatherDescription()).isEqualTo("rozproszone chmury");
        assertThat(forecast.getCloudiness()).isEqualTo(44);
        assertThat(forecast.getWindSpeed()).isEqualTo(4.35F);
        assertThat(forecast.getWindDegree()).isEqualTo(195);
        assertThat(forecast.getRainPrecipitation()).isEqualTo(null);
        assertThat(forecast.getSnowPrecipitation()).isEqualTo(null);
        assertThat(forecast.getVisibility()).isEqualTo(370);
        assertThat(forecast.getProbabilityOfPrecipitation()).isEqualTo(0);
        assertThat(forecast.getDateAndTime()).isEqualTo("2021-02-21 12:00:00");
    }

    @Test
    void mapToForecastFromForecastDtoTest() {
        //given
        ForecastMapper forecastMapper = new ForecastMapper();

        //when
        Forecast forecast = forecastMapper.mapToForecast(createForecastDtoForTest());

        //then
        assertThat(forecast).isExactlyInstanceOf(Forecast.class);
        assertThat(forecast.getTemp()).isEqualTo(5.5F);
        assertThat(forecast.getTempFeelsLike()).isEqualTo(0.74F);
        assertThat(forecast.getPressureAtSeaLevelhPa()).isEqualTo(1020);
        assertThat(forecast.getPressureAtGroundLevelhPa()).isEqualTo(1020);
        assertThat(forecast.getRelativeHumidity()).isEqualTo(77);
        assertThat(forecast.getWeatherDescription()).isEqualTo("rozproszone chmury");
        assertThat(forecast.getCloudiness()).isEqualTo(44);
        assertThat(forecast.getWindSpeed()).isEqualTo(4.35F);
        assertThat(forecast.getWindDegree()).isEqualTo(195);
        assertThat(forecast.getRainPrecipitation()).isEqualTo(null);
        assertThat(forecast.getSnowPrecipitation()).isEqualTo(null);
        assertThat(forecast.getVisibility()).isEqualTo(370);
        assertThat(forecast.getProbabilityOfPrecipitation()).isEqualTo(0);
        assertThat(forecast.getDateAndTime()).isEqualTo("2021-02-21 12:00:00");
    }

    @Test
    void mapToForecastDtoFromSingleForecast() {
        //given
        ForecastMapper forecastMapper = new ForecastMapper();

        //when
        ForecastDto forecastDto = forecastMapper.mapToForecastDto(createSingleForecast());

        //then
        assertThat(forecastDto).isExactlyInstanceOf(ForecastDto.class);
        assertThat(forecastDto.getTemp()).isEqualTo(5.5F);
        assertThat(forecastDto.getTempFeelsLike()).isEqualTo(0.74F);
        assertThat(forecastDto.getPressureAtSeaLevelhPa()).isEqualTo(1020);
        assertThat(forecastDto.getPressureAtGroundLevelhPa()).isEqualTo(1020);
        assertThat(forecastDto.getRelativeHumidity()).isEqualTo(77);
        assertThat(forecastDto.getWeatherDescription()).isEqualTo("rozproszone chmury");
        assertThat(forecastDto.getCloudiness()).isEqualTo(44);
        assertThat(forecastDto.getWindSpeed()).isEqualTo(4.35F);
        assertThat(forecastDto.getWindDegree()).isEqualTo(195);
        assertThat(forecastDto.getRainPrecipitation()).isEqualTo(null);
        assertThat(forecastDto.getSnowPrecipitation()).isEqualTo(null);
        assertThat(forecastDto.getVisibility()).isEqualTo(370);
        assertThat(forecastDto.getProbabilityOfPrecipitation()).isEqualTo(0);
        assertThat(forecastDto.getDateAndTime()).isEqualTo("2021-02-21 12:00:00");
    }
}
