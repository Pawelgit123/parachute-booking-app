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
    private float temp;
    private float tempFeelsLike;
    private int pressureAtSeaLevelhPa;
    private int pressureAtGroundLevelhPa;
    private int relativeHumidity;
    private String weatherDescription;
    private int cloudiness;
    private float windSpeed;
    private int windDegree;
    @Nullable
    private float rainPrecipitation;
    @Nullable
    private float snowPrecipitation;
    private int visibility;
    private float probabilityOfPrecipitation;
    private String dateAndTime;
}
