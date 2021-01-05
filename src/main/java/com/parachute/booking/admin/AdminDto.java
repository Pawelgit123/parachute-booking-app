package com.parachute.booking.admin;

import com.sun.istack.NotNull;
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
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
