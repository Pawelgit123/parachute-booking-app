package com.parachute.booking.admin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


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
    @NotNull(message = "login has to be provided")
    @Length(min = 5, message = "Login has to have at least 5 chars")
    private String login;
    @NotNull
    @Length(min = 5, message = "password needs to have 5 chars")
    private String password;
    @NotNull
    @Email
    private String email;
}
