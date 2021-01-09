package com.parachute.booking.forecast.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
class ForecastResponse {
    @JsonProperty("list")
    private List<SingleForecast> singleForecastList;

    @Data
    @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
    static class SingleForecast {
        @JsonProperty("main")
        private General general;
        private Weather weather;
        private Clouds clouds;
        private Wind wind;
        private int visibility;
        private float pop;
        private Rain rain;
        private Snow snow;
        @JsonProperty("dt_txt")
        private String dateAndTime;

        public Optional<Rain> getRain() {
            return Optional.ofNullable(rain);
        }

        @Data
        @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
        static class General {
            private float temp;
            @JsonProperty("feels_like")
            private float feelsLike;
            private int seaLevel;
            private int grnd_level;
            private int humidity;
        }

        @Data
        static class Weather {
            private String description;
        }

        @Data
        static class Clouds {
            private int all;
        }

        @Data
        static class Wind {
            private float speed;
            private int deg;
        }

        @Data
        static class Rain {
            @JsonProperty("3h")
            private float precipitationHeight;
        }

        @Data
        static class Snow {
            @JsonProperty("3h")
            private float precipitationHeight;
        }
    }
}
