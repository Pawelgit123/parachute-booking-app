package com.parachute.booking.forecast;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class DayCreator {

    public String createCalendarDayFromNumbers(int year, int month, int day){
        return year+"-"+month+"-"+day;
    }
}
