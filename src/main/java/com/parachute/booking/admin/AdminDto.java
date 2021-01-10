package com.parachute.booking.admin;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

    private Long id;
    @NotNull(message = "Logins has to be provided")
    @Length(min = 5, max = 25, message = "Login size out of bound (5-25 letters)")
    private String login;
    @NotNull(message = "Password has to be provided")
    @Length(min = 5, max = 25, message = "Password size out of bound (5-25 letters)")
    private String password;
    @NotNull(message = "Email has to be provided")
    @Email(message = "Email address is inappropriate")
    private String email;
}
