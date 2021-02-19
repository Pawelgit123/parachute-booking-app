package com.parachute.booking.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
    private String firstName;
    private String lastName;
    private String pesel;
    private String phoneNumber;
    private String email;
}
