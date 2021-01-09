package com.parachute.booking.forecast.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
class ForecastResponse {
    @JsonProperty("list")
    private List<SingleForecast> singleForecastList;

    @Data
    static class SingleForecast {
        @JsonProperty("main")
        private General general;
        private Weather weather;
        private Clouds clouds;
        private Wind wind;
        private int visibility;
        private float pop;
        private Optional<Rain> rain;
        private Optional<Snow> snow;
        @JsonProperty("dt_txt")
        private String dateAndTime;

        @Data
        static class General {
            private float temp;
            private float feels_like;
            private int sea_level;
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
