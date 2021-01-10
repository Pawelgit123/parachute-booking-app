package com.parachute.booking.forecast.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
public class ForecastResponse {
    @JsonProperty("list")
    private List<SingleForecast> singleForecastList;

    @Data
    public static class SingleForecast {
        @JsonProperty("main")
        private General general;
        private Weather weather;
        private Clouds clouds;
        private Wind wind;
        private Integer visibility; // 0
        private Float pop;
        private Rain rain;
        private Snow snow;
        @JsonProperty("dt_txt")
        private String dateAndTime;

        @Data
        @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
        public static class General {
            private float temp;
            private float feelsLike;
            private int seaLevel;
            private int grndLevel;
            private int humidity;
        }

        @Data
        public static class Weather {
            private String description;
        }

        @Data
        public static class Clouds {
            private int all;
        }

        @Data
        public static class Wind {
            private float speed;
            private int deg;
        }

        @Data
        public static class Rain {
            @JsonProperty("3h")
            private float precipitationHeight;
        }

        @Data
        public static class Snow {
            @JsonProperty("3h")
            private float precipitationHeight;
        }
    }
}
