package com.parachute.booking.plane;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaneDto {

    private Long id;
    private Long planeNumber;
    private String planeModel;
}
