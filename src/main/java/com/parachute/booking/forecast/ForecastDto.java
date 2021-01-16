package com.parachute.booking.forecast;

import com.sun.istack.Nullable;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
public class ForecastDto {

    private Long id;
    private Float temp;
    private Float tempFeelsLike;
    private Integer pressureAtSeaLevelhPa;
    private Integer pressureAtGroundLevelhPa;
    private Integer relativeHumidity;
    private String weatherDescription;
    private Integer cloudiness;
    private Float windSpeed;
    private Integer windDegree;
    @Nullable
    private Float rainPrecipitation;
    @Nullable
    private Float snowPrecipitation;
    private Integer visibility;
    private Float probabilityOfPrecipitation;
    private String dateAndTime;
}
