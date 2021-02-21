package com.parachute.booking.forecast.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ForecastResponse {
    @JsonProperty("list")
    private List<SingleForecast> singleForecastList;

    @Builder
    @Data
    public static class SingleForecast {
        @JsonProperty("main")
        private General general;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;
        private Integer visibility;
        private Float pop;
        private Rain rain;
        private Snow snow;
        @JsonProperty("dt_txt")
        private String dateAndTime;

        @Builder
        @Data
        @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
        public static class General {
            private Float temp;
            private Float feelsLike;
            private Integer seaLevel;
            private Integer grndLevel;
            private Integer humidity;
        }

        @Builder
        @Data
        public static class Weather {
            private String description;
        }

        @Builder
        @Data
        public static class Clouds {
            private Integer all;
        }

        @Builder
        @Data
        public static class Wind {
            private Float speed;
            private Integer deg;
        }

        @Builder
        @Data
        public static class Rain {
            @JsonProperty("3h")
            private Float precipitationHeight;
        }

        @Builder
        @Data
        public static class Snow {
            @JsonProperty("3h")
            private Float precipitationHeight;
        }
    }
}
