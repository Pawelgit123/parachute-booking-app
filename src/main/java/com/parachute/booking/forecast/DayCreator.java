package com.parachute.booking.forecast;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class DayCreator {

    public String createCalendarDayFromNumbers(int year, int month, int day) {
        //input type="date"
        return year+"-"+month+"-"+day;
    }
}
