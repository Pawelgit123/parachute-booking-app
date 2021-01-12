package com.parachute.booking.forecast;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
