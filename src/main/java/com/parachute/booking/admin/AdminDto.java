package com.parachute.booking.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

    private Long id;

    private String login;
    private String password;
    private String email;
}
